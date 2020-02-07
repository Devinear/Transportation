package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BusInfoModel
import com.custom.transportation.repository.unit.BusInfoData

class BusInfoPresenter(val view: BusInfoContract.View) : BusInfoContract.Presenter {

    private val model = BusInfoModel(this)

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
