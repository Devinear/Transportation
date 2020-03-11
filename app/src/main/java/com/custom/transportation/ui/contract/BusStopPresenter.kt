package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkDataSource
import com.custom.transportation.repository.BookmarkDataSourceImpl
import com.custom.transportation.repository.BusStopData
import com.custom.transportation.repository.BusStopDataSourceImpl
import com.custom.transportation.repository.mapper.BusStopMapperImpl

class BusStopPresenter(val view: BusStopContract.View) : BusStopContract.Presenter, BusStopContract.Callback {

    private val busStop = BusStopDataSourceImpl.INSTANCE
    private val bookmark : BookmarkDataSource = BookmarkDataSourceImpl.INSTANCE

    override fun search(search: String) = busStop.search(search, this)

    override fun getData(): List<BusStopData> = busStop.getAll()

    override fun addBookmark(bookmark: BusStopData) : Boolean = this.bookmark.insert(BusStopMapperImpl.toBookmark(data = bookmark))

    override fun deleteBookmark(bookmark: BusStopData): Boolean = this.bookmark.delete(BusStopMapperImpl.toBookmark(data = bookmark))

    override fun existBookmark(bookmark: BusStopData): Int = this.bookmark.isExist(BusStopMapperImpl.toBookmark(data = bookmark))

    override fun onSuccess() = view.searchSuccess()

    override fun onFailure(msg: String) = view.searchFailure(msg)
}