package com.custom.transportation.repository

import com.custom.transportation.base.BaseContract

interface BookmarkDataSource {

    fun insert(bookmark : Any)

    fun delete(bookmark : Any) : Boolean

    fun isDuplicate(bookmark: Any) : Boolean

    fun getAll() : List<Any>

    fun reloadData(callback: BaseContract.LocalCallback)
}
