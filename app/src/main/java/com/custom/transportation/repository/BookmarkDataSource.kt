package com.custom.transportation.repository

interface BookmarkDataSource {

    fun getAll() : List<BookmarkData>

    fun insert(bookmark : BookmarkData) : Boolean

    fun isExist(bookmark : BookmarkData) : Int

    fun delete(bookmark : BookmarkData) : Boolean

    fun update(bookmark: BookmarkData) : Boolean

    suspend fun move(fromIndex: Int, toIndex: Int)

    suspend fun reloadData()
}
