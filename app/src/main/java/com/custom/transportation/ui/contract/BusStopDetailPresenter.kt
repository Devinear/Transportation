package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BusStopDetailModel
import com.custom.transportation.repository.unit.BusInfoData

class BusStopDetailPresenter(val view: BusStopDetailContract.View) : BusStopDetailContract.Presenter {

    private val model = BusStopDetailModel(this)

    override fun searchArsId(arsId: Int) = model.searchArsId(arsId)

    override fun addBookmark(data: BusInfoData) = model.addBookmark(data)

    override fun getBusInfoData(): List<BusInfoData> = model.getBusStopData()

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess(items : List<BusInfoData>) = view.searchSuccess(items)
}
