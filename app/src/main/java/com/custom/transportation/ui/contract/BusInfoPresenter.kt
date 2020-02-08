package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel
import com.custom.transportation.repository.model.BusInfoData
import com.custom.transportation.repository.model.BusInfoModel

class BusInfoPresenter(val view: BusInfoContract.View) : BusInfoContract.Presenter {

    private val busInfoModel = BusInfoModel.getInstance(this)
    private val bookmarkModel = BookmarkModel.getInstance()

    override fun getData(): List<BusInfoData> = busInfoModel.getBusStopData()

    override fun search(search: String) = busInfoModel.searchArsId(search)

    override fun addBookmark(bookmark: Any) = bookmarkModel.addBookmark(bookmark)

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess() = view.searchSuccess()
}
