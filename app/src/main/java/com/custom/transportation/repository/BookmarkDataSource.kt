package com.custom.transportation.repository

interface BookmarkDataSource {

    fun insert(data : BusInfoData) : Boolean

    fun insert(data : BusStopData) : Boolean

    fun delete(bookmark : BookmarkData) : Boolean

    suspend fun move(fromIndex: Int, toIndex: Int)

    fun getAll() : List<Any>

    suspend fun reloadData()
}
