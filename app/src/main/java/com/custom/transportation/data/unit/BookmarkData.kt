package com.custom.transportation.data.unit

data class BookmarkData constructor(val bookmark: Any)

object BookmarkDatabase {

    private var database : ArrayList<BookmarkData> = ArrayList()

    fun add(data: Any) : Int {
        database.add(BookmarkData(data))
        return database.lastIndex
    }

    fun get(index: Int) : Any = database[index].bookmark

    fun count() : Int = database.size

    fun clear() = database.clear()
}