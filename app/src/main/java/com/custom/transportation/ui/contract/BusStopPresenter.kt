package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel
import com.custom.transportation.repository.model.BusStopData
import com.custom.transportation.repository.model.BusStopModel

class BusStopPresenter(val view: BusStopContract.View) : BusStopContract.Presenter {

    private val busStopModel = BusStopModel.getInstance(this)
    private val bookmarkModel = BookmarkModel.getInstance()

    override fun search(search: String) = busStopModel.searchWord(search)

    override fun getData(): List<BusStopData> = busStopModel.getBusStopData()

    override fun addBookmark(bookmark: Any) = bookmarkModel.addBookmark(bookmark)

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess() = view.searchSuccess()
}