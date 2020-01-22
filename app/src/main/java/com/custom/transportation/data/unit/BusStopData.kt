package com.custom.transportation.data.unit

class BusStopData private constructor(val index: Int, val arsId: Int, posX: Float, posY: Float, stId: Int, val stNm: String, tmX: Float, tmY: Float){

    class Builder(private val index: Int) {

        private var arsId: Int      = -1
        private var posX : Float    = -1f
        private var posY : Float    = -1f
        private var stId : Int      = -1
        private var stNm : String   = ""
        private var tmX  : Float    = -1f
        private var tmY  : Float    = -1f

        fun setArsId(arsId: Int) : Builder {
            this.arsId = arsId
            return this
        }
        fun setPosX(posX: Float) : Builder {
            this.posX = posX
            return this
        }
        fun setPosY(posY: Float) : Builder {
            this.posY = posY
            return this
        }
        fun setStId(stId: Int) : Builder {
            this.stId = stId
            return this
        }
        fun setStNm(stNm: String) : Builder {
            this.stNm = stNm
            return this
        }
        fun setTmX(tmX: Float) : Builder {
            this.tmX = tmX
            return this
        }
        fun setTmY(tmY: Float) : Builder {
            this.tmY = tmY
            return this
        }

        fun build() = BusStopData(index, arsId, posX, posY, stId, stNm, tmX, tmY)
    }
}

object BusStopDatabase {

    private var database : ArrayList<BusStopData> = ArrayList()

    fun add(data: BusStopData) : Int {
        database.add(data)
        return database.lastIndex
    }

    fun get(index: Int) : BusStopData = database[index]

    fun count() : Int = database.size

    fun clear() = database.clear()

    @Suppress("UNCHECKED_CAST")
    fun clone() = database.clone() as ArrayList<BusStopData>
}