package com.custom.transportation.repository.model

import com.custom.transportation.base.BaseContract
import com.custom.transportation.common.CommonData
import com.custom.transportation.repository.local.Bookmark
import com.custom.transportation.repository.local.BookmarkDB
import com.custom.transportation.repository.local.BookmarkDao

class BookmarkModel(private var presenter: BaseContract.Presenter) {

    private val bookmarks : ArrayList<Any> = ArrayList()

    fun reloadData() {
        Thread(Runnable {
            val items: List<Bookmark> = loadDatabaseDao()?.getAll() ?: return@Runnable
            for(item: Bookmark in items) {
                @Suppress("IMPLICIT_CAST_TO_ANY")
                val data: Any? = if(item.isBusStop) item.station else item.busInfo
                data ?: return@Runnable
                bookmarks.add(data)
            }
            presenter.updateBookmark()
        }).start()
    }

    fun getBookmarkAll() : List<Any> = bookmarks

    fun addBookmark(bookmark : Any) {
        if(isDuplicate(bookmark)) return
        bookmarks.add(bookmark)
        synchronized(this) {
            Thread(Runnable {
                when (bookmark) {
                    is BusStopData -> loadDatabaseDao()?.insertBookmark(Bookmark(true, bookmark, null))
                    is BusInfoData -> loadDatabaseDao()?.insertBookmark(Bookmark(false, null, bookmark))
                }
            }).start()
        }
    }

    fun deleteBookmark(bookmark: Any) : Boolean {
        synchronized(this) {
            Thread(Runnable {
                when (bookmark) {
                    is BusStopData -> loadDatabaseDao()?.deleteBookmark(Bookmark(true,bookmark,null))
                    is BusInfoData -> loadDatabaseDao()?.deleteBookmark(Bookmark(false,null,bookmark))
                }
            }).start()
        }
        return bookmarks.remove(bookmark)
    }

    private fun isDuplicate(bookmark: Any) : Boolean {
        if(bookmarks.size == 0) return false
        for(item in bookmarks) {
            if(item == bookmark) return true
        }
        return false
    }

    private fun loadDatabaseDao() : BookmarkDao? {
        CommonData.appContext ?: return null
        return BookmarkDB.getInstance(CommonData.appContext!!).bookmarkDao()
    }

    companion object {
        private var instance : BookmarkModel? = null
        fun getInstance(newPresenter: BaseContract.Presenter) : BookmarkModel {
            instance ?: return BookmarkModel(newPresenter).also {
                it.presenter = newPresenter
                instance = it
            }
            return instance!!
        }
    }
}