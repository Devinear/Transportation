package com.custom.transportation.ui.contract

import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.repository.BusStopData

interface BusStopContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<BusStopData>

    interface Callback : BaseContract.RemoteCallback
}
