package com.custom.transportation.presenter

import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.model.BusStopModel

interface BusStop {
    interface View {
        fun searchSuccess(items : List<BusStopData>)
        fun searchFailure(msg : String)
    }

    interface Presenter {
        fun searchWord(search: String)
        fun addBookmark(data: BusStopData)
        fun getBusStopData() : List<BusStopData>
    }
}

class BusStopPresenter(val view: BusStop.View) : BusStop.Presenter {

    private val model = BusStopModel(this)

    override fun searchWord(search: String) = model.searchWord(search)

    override fun addBookmark(data: BusStopData) = model.addBookmark(data)

    override fun getBusStopData(): List<BusStopData> = model.getBusStopData()

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess(items : List<BusStopData>) = view.searchSuccess(items)
}