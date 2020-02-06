package com.custom.transportation.ui.contract

import com.custom.transportation.repository.unit.BusStopData
import com.custom.transportation.repository.model.BusStopModel

interface BusStopContract {
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
