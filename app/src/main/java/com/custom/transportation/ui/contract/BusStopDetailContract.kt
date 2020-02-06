package com.custom.transportation.ui.contract

import com.custom.transportation.repository.unit.BusInfoData
import com.custom.transportation.repository.model.BusStopDetailModel

interface BusStopDetailContract {
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
