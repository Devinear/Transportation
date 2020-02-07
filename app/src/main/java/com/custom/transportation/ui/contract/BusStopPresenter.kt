package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BusStopModel
import com.custom.transportation.repository.unit.BusStopData

class BusStopPresenter(val view: BusStopContract.View) : BusStopContract.Presenter {

    private val model = BusStopModel(this)

    override fun search(search: String) = model.searchWord(search)

    override fun getData(): List<BusStopData> = model.getBusStopData()

    override fun addBookmark(bookmark: Any) {
        if(bookmark is BusStopData)
            model.addBookmark(bookmark)
    }

    /* Search CallBack */
    fun searchFailure(msg: String) = view.searchFailure(msg)

    fun searchSuccess() = view.searchSuccess()
}