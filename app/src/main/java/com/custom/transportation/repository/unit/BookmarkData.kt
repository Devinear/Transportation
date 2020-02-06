package com.custom.transportation.repository.unit

object BookmarkDatabase {

    private val database : ArrayList<Any> = ArrayList()

    fun add(data: BusStopData) = database.add(data)

    fun add(data: BusInfoData) = database.add(data)

    fun get(index: Int) : Any = database[index]

    fun getAll() = database
}