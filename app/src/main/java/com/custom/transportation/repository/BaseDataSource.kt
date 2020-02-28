package com.custom.transportation.repository

import com.custom.transportation.ui.base.BaseContract

interface BaseDataSource <T> {
    fun search(search: String, callback: BaseContract.RemoteCallback)
    fun getAll() : List<T>
}