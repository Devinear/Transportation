package com.custom.transportation.repository

import com.custom.transportation.base.BaseContract

interface BusStopDataSource {
    fun search(search: String, callback: BaseContract.RemoteCallback)
    fun getAll() : List<BusStopData>
}