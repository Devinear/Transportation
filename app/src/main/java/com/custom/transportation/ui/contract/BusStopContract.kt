package com.custom.transportation.ui.contract

import com.custom.transportation.base.BaseContract
import com.custom.transportation.repository.unit.BusStopData
import com.custom.transportation.repository.model.BusStopModel

interface BusStopContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun getData() : List<BusStopData>
    }
}
