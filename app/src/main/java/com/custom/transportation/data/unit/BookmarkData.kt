package com.custom.transportation.data.unit

data class BookmarkData constructor(val bookmark: Any)

object BookmarkDatabase {

    private var database : ArrayList<BookmarkData> = ArrayList()

    fun add(data: Any) = database.add(BookmarkData(data))

    fun get(index: Int) : Any = database[index].bookmark

    fun getAll() = database
}