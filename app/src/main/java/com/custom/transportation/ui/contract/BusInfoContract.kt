package com.custom.transportation.ui.contract

import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.repository.BusInfoData

interface BusInfoContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<BusInfoData>

    interface RemoteCallback : BaseContract.RemoteCallback
}
