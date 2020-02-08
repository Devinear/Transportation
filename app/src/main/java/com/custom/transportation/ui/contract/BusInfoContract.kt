package com.custom.transportation.ui.contract

import com.custom.transportation.base.BaseContract
import com.custom.transportation.repository.model.BusInfoData

interface BusInfoContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun getData() : List<BusInfoData>
    }
}
