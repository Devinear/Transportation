package com.custom.transportation.repository

import android.util.Log
import com.custom.transportation.common.CommonData
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.repository.local.Bookmark
import com.custom.transportation.repository.local.BookmarkDB
import com.custom.transportation.repository.local.BookmarkDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

data class BookmarkData
    (var key: Int, val isBusInfo: Boolean,
    val name: String, val firValue: String, val secValue: String)
//     val busNm: String?, val busType: String?, val routeType: String?,
//     val stNm: String?, val stId: String?, val arsId: String?)

class BookmarkDataSourceImpl : BookmarkDataSource {

    // BookmarkData를 기반으로 해당 DB에 매칭되는 ID값을 보관한다.
    private val bookmarks = mutableMapOf<Int, BookmarkData>()

    override fun insert(data: BusInfoData): Boolean = insert(fromBusInfoData(data))

    override fun insert(data: BusStopData): Boolean = insert(fromBusStopData(data))

    private fun insert(bookmark: BookmarkData): Boolean{
        if(bookmarks.containsValue(bookmark)) return false

        bookmark.key = bookmarks.keys.last() + 1
        CoroutineScope(Dispatchers.IO).launch {
            loadDatabaseDao()?.insertBookmark(Bookmark(bookmark.key,bookmark))
        }
        bookmarks[bookmark.key] = bookmark
        return true
    }

    override fun delete(bookmark: BookmarkData) : Boolean {
        val data = bookmarks.remove(bookmark.key)
        data ?: return false

        CoroutineScope(Dispatchers.IO).launch {
            loadDatabaseDao()?.deleteBookmark(Bookmark(bookmark.key, bookmark))
        }
        return true
    }

    override suspend fun move(fromIndex: Int, toIndex: Int) {
        Log.d("DB", "move:${fromIndex}>${toIndex}")

        val job = CoroutineScope(Dispatchers.IO).launch {

            val list = loadDatabaseDao()?.getBetweenIndex(min(fromIndex, toIndex), max(fromIndex, toIndex))
            list ?: return@launch
        }
    }

    override suspend fun reloadData() {
        val job = CoroutineScope(Dispatchers.IO).launch {

            val items: List<Bookmark>? = loadDatabaseDao()?.getAll()
            // items가 Null인 경우 Coroutine 종료
            items ?: cancel()

            bookmarks.clear()
            for(item: Bookmark in items!!) {
                bookmarks[item.id] = item.data
            }

            // key(id) 값으로 정렬
            bookmarks.toSortedMap()
        }
        // job이 종료될때까지 join하게 되면 callback이 필요없다.
        job.join()
    }

    override fun getAll(): List<BookmarkData> = bookmarks.values.toMutableList()

    private fun loadDatabaseDao() : BookmarkDao? {
        CommonData.appContext ?: return null
        return BookmarkDB.getInstance(CommonData.appContext!!).bookmarkDao()
    }

    private fun fromBusInfoData(data: BusInfoData) : BookmarkData
            = BookmarkData(key = -1, isBusInfo = true, name = data.name,
        firValue = ConvertUtil.toBusType(data.thisType), secValue = ConvertUtil.toRouteType(data.routeType))

    private fun fromBusStopData(data: BusStopData) : BookmarkData
            = BookmarkData(key = -1, isBusInfo = false, name = data.stNm,
        firValue = data.stId, secValue = data.arsId)

    companion object {
        private var INSTANCE : BookmarkDataSource? = null
        fun getInstance() : BookmarkDataSource =
            INSTANCE ?: BookmarkDataSourceImpl().also { INSTANCE = it }
    }
}