package com.custom.transportation.data.unit

class BusInfoData private constructor
    (val name: String, val time: String, val direction: String, val before: String, val after: String) {
    companion object {
        fun makeData(name: String, time: String, direction: String, before: String, after: String)
                = BusInfoData(name, time, direction, before, after)
    }
}

object BusInfoDatabase {

    private val database : ArrayList<BusInfoData> = ArrayList()

    fun add(name: String, time: String, direction: String, before: String, after: String) : Int {
        database.add(BusInfoData.makeData(name, time, direction, before, after))
        return database.lastIndex
    }

    fun get(index: Int) : BusInfoData = database[index]

    fun count() : Int = database.size

    fun clear() = database.clear()

    @Suppress("UNCHECKED_CAST")
    fun clone() = database.clone() as ArrayList<BusInfoData>
}