package com.custom.transportation.presenter

import com.custom.transportation.data.unit.BusInfoData
import com.custom.transportation.model.BusStopDetailModel

interface BusStopDetail {
    interface View {
        fun searchSuccess(items : List<BusInfoData>)
        fun searchFailure(msg : String)
    }

    interface Presenter {
        fun searchArsId(arsId: Int)
        fun addBookmark(data: BusInfoData)
        fun getBusInfoData() : List<BusInfoData>
    }
}

class BusStopDetailPresenter(val view: BusStopDetail.View) : BusStopDetail.Presenter {

    private val model = BusStopDetailModel(this)

    override fun searchArsId(arsId: Int) = model.searchArsId(arsId)

    override fun addBookmark(data: BusInfoData) = model.addBookmark(data)

    override fun getBusInfoData(): List<BusInfoData> = model.getBusStopData()

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess(items : List<BusInfoData>) = view.searchSuccess(items)
}