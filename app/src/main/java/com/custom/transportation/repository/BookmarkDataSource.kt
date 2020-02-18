package com.custom.transportation.repository

interface BookmarkDataSource {

    fun insert(bookmark : Any)

    fun delete(bookmark : Any) : Boolean

    suspend fun move(fromIndex: Int, toIndex: Int)

    fun isDuplicate(bookmark: Any) : Boolean

    fun getAll() : List<Any>

    suspend fun reloadData()
}
