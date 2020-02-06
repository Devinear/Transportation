package com.custom.transportation.repository.unit

class BusStopData private constructor
    (val arsId: Int,  val stId: Int, val stNm: String, val tmX: Float, val tmY: Float) {
    companion object {
        fun makeData(arsId: Int, stId: Int, stNm: String, tmX: Float, tmY: Float)
                = BusStopData(arsId, stId, stNm, tmX, tmY)
    }
}

object BusStopDatabase {

    private val database : ArrayList<BusStopData> = ArrayList()

    fun add(arsId: Int, stId: Int, stNm: String, tmX: Float, tmY: Float)
            = database.add(BusStopData.makeData(arsId, stId, stNm, tmX, tmY))

    fun get(index: Int) : BusStopData = database[index]

    fun getAll() = database

    fun count() : Int = database.size

    fun clear() = database.clear()
}