package com.custom.transportation.repository

interface BookmarkDataSource : BaseDataSource<BookmarkData> {

    override fun getAll() : List<BookmarkData>

    fun insert(data : BusInfoData) : Boolean

    fun insert(data : BusStopData) : Boolean

    fun delete(bookmark : BookmarkData) : Boolean

    fun update(bookmark: BookmarkData) : Boolean

    suspend fun move(fromIndex: Int, toIndex: Int)

    suspend fun reloadData()
}
