package com.custom.transportation.repository

import com.custom.transportation.base.BaseContract

interface BusInfoDataSource {
    fun search(arsId: String, callback: BaseContract.RemoteCallback)
    fun getAll() : List<BusInfoData>
}