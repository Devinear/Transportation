package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BusStopModel
import com.custom.transportation.repository.unit.BusStopData

class BusStopPresenter(val view: BusStopContract.View) : BusStopContract.Presenter {

    private val model = BusStopModel(this)

    override fun searchWord(search: String) = model.searchWord(search)

    override fun addBookmark(data: BusStopData) = model.addBookmark(data)

    override fun getBusStopData(): List<BusStopData> = model.getBusStopData()

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess(items : List<BusStopData>) = view.searchSuccess(items)
}