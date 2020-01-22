package com.custom.transportation.data.unit

data class BookmarkData constructor(val bookmark: Any)

object BookmarkDatabase {

    private var database : ArrayList<BookmarkData> = ArrayList()

    fun add(data: Any) : Int {
        database.add(BookmarkData(data))
        return database.lastIndex
    }

    fun get(index: Int) : Any = database[index].bookmark

    fun isBusStop(index: Int) : Boolean = database[index].bookmark is BusStopData

    fun isBusInfo(index: Int) : Boolean = database[index].bookmark is BusInfoData

    fun count() : Int = database.size

    fun clear() = database.clear()
}