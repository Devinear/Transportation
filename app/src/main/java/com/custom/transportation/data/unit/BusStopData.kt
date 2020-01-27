package com.custom.transportation.data.unit

class BusStopData private constructor
    (val arsId: Int, val posX: Float, val posY: Float, val stId: Int, val stNm: String, val tmX: Float, val tmY: Float) {
    companion object {
        fun makeData(arsId: Int, posX: Float, posY: Float, stId: Int, stNm: String, tmX: Float, tmY: Float)
                = BusStopData(arsId, posX, posY, stId, stNm, tmX, tmY)
    }
}

object BusStopDatabase {

    private var database : ArrayList<BusStopData> = ArrayList()

    fun add(arsId: Int, posX: Float, posY: Float, stId: Int, stNm: String, tmX: Float, tmY: Float) : Int {
        database.add(BusStopData.makeData(arsId, posX, posY, stId, stNm, tmX, tmY))
        return database.lastIndex
    }

    fun get(index: Int) : BusStopData = database[index]

    fun count() : Int = database.size

    fun clear() = database.clear()

    @Suppress("UNCHECKED_CAST")
    fun clone() = database.clone() as ArrayList<BusStopData>
}