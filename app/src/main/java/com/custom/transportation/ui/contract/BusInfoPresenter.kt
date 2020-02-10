package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel
import com.custom.transportation.repository.model.BusInfoData
import com.custom.transportation.repository.model.BusInfoModel

class BusInfoPresenter(val view: BusInfoContract.View) : BusInfoContract.Presenter {

    override fun getData(): List<BusInfoData> = BusInfoModel.getInstance(this).getBusStopData()

    override fun search(search: String) = BusInfoModel.getInstance(this).searchArsId(search)

    override fun addBookmark(bookmark: Any) = BookmarkModel.getInstance(this).addBookmark(bookmark)

    override fun updateBookmark() = Unit

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess() = view.searchSuccess()
}
