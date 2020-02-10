package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel
import com.custom.transportation.repository.model.BusStopData
import com.custom.transportation.repository.model.BusStopModel

class BusStopPresenter(val view: BusStopContract.View) : BusStopContract.Presenter {

    override fun search(search: String) = BusStopModel.getInstance(this).searchWord(search)

    override fun getData(): List<BusStopData> = BusStopModel.getInstance(this).getBusStopData()

    override fun addBookmark(bookmark: Any) = BookmarkModel.getInstance(this).addBookmark(bookmark)

    override fun updateBookmark() = Unit

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess() = view.searchSuccess()
}