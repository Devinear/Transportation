package com.custom.transportation.ui.contract

import androidx.lifecycle.LiveData
import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.repository.BusInfoData

interface BusInfoContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<BusInfoData> {
        fun getLiveData() : List<LiveData<BusInfoData>>
    }

    interface RemoteCallback : BaseContract.RemoteCallback
}
