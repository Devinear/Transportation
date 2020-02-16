package com.custom.transportation.repository

import com.custom.transportation.common.CommonData
import com.custom.transportation.repository.local.Bookmark
import com.custom.transportation.repository.local.BookmarkDB
import com.custom.transportation.repository.local.BookmarkDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class BookmarkDataSourceImpl : BookmarkDataSource {

    private val bookmarks : ArrayList<Any> = ArrayList()

    override fun insert(bookmark: Any) {
        if(isDuplicate(bookmark)) return

        bookmarks.add(bookmark)

        CoroutineScope(Dispatchers.IO).launch {
            when (bookmark) {
                is BusStopData -> loadDatabaseDao()?.insertBookmark(Bookmark(true, bookmark, null))
                is BusInfoData -> loadDatabaseDao()?.insertBookmark(Bookmark(false, null, bookmark))
            }
        }
    }

    override fun delete(bookmark: Any) : Boolean {
        if(!isDuplicate(bookmark)) return false

        CoroutineScope(Dispatchers.IO).launch {
            when (bookmark) {
                is BusStopData -> loadDatabaseDao()?.deleteBookmark(Bookmark(true,bookmark,null))
                is BusInfoData -> loadDatabaseDao()?.deleteBookmark(Bookmark(false,null,bookmark))
            }
        }

        return bookmarks.remove(bookmark)
    }

    override fun isDuplicate(bookmark: Any) : Boolean {
        if(bookmarks.size == 0) return false
        for(item in bookmarks) {
            if(item == bookmark) return true
        }
        return false
    }

    override suspend fun reloadData() {
        val job = CoroutineScope(Dispatchers.IO).launch {

            val items: List<Bookmark>? = loadDatabaseDao()?.getAll()
            // items가 Null인 경우 Coroutine 종료
            items ?: cancel()

            bookmarks.clear()
            for(item: Bookmark in items!!) {
                @Suppress("IMPLICIT_CAST_TO_ANY")
                val data: Any? = if(item.isBusStop) item.station else item.busInfo
                data ?: cancel()
                bookmarks.add(data!!)
            }
        }

        // job이 종료될때까지 join하게 되면 callback이 필요없다.
        job.join()
    }

    override fun getAll(): List<Any> = bookmarks

    private fun loadDatabaseDao() : BookmarkDao? {
        CommonData.appContext ?: return null
        return BookmarkDB.getInstance(CommonData.appContext!!).bookmarkDao()
    }

    companion object {
        private var INSTANCE : BookmarkDataSource? = null
        fun getInstance() : BookmarkDataSource =
            INSTANCE ?: BookmarkDataSourceImpl().also { INSTANCE = it }
    }
}