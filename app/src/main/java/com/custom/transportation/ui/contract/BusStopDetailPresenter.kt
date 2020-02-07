package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BusStopDetailModel
import com.custom.transportation.repository.unit.BusInfoData

class BusStopDetailPresenter(val view: BusStopDetailContract.View) : BusStopDetailContract.Presenter {

    private val model = BusStopDetailModel(this)

    override fun getData(): List<BusInfoData> = model.getBusStopData()

    override fun search(search: String) = model.searchArsId(search)

    override fun addBookmark(bookmark: Any) {
        if(bookmark is BusInfoData)
            model.addBookmark(bookmark)
    }

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess(items : List<BusInfoData>) = view.searchSuccess()
}
