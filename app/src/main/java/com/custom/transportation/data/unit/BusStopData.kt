package com.custom.transportation.data.unit

class BusStopData private constructor(val index: Int, val arsId: Int, posX: Float, posY: Float, stId: Int, val stNm: String, tmX: Float, tmY: Float){

    class Builder(val index: Int) {

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

    fun getIndexByName(start: Int, stNm: String) : Int {
        for(i in start until database.size) {
            if(database[i].stNm.contains(stNm, false))
                return i
        }
        return -1
    }

    // 정류소 번호이지민 String 비교를 통해 이름을 찾는 것과 동일하게 찾도록 한다.
    fun getIndexByArsId(start: Int, arsId: Int) : Int {
        for(i in start until database.size) {
            if(database[i].arsId.toString().contains(arsId.toString(), false))
                return i
        }
        return -1
    }

    fun clone() = database.clone() as ArrayList<BusStopData>
}