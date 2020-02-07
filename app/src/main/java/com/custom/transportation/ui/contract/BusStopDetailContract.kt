package com.custom.transportation.ui.contract

import com.custom.transportation.base.BaseContract
import com.custom.transportation.repository.unit.BusInfoData
import com.custom.transportation.repository.model.BusStopDetailModel

interface BusStopDetailContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun getData() : List<BusInfoData>
    }
}
