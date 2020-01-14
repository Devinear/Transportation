package com.custom.transportation.data.unit

class BusInfoData private constructor(val index: Int, val name: String, val time: String, val direction: String, val before: String, val after: String){

    class Builder(val index: Int) {

        private var name        : String = ""
        private var time        : String = ""
        private var direction   : String = ""
        private var before      : String = ""
        private var after       : String = ""

        fun setName(name: String) : Builder {
            this.name = name
            return this
        }
        fun setTime(time: String) : Builder {
            this.time = time
            return this
        }
        fun setDirection(direction: String) : Builder {
            this.direction = direction
            return this
        }
        fun setBefore(before: String) : Builder {
            this.before = before
            return this
        }
        fun setAfter(after: String) : Builder {
            this.after = after
            return this
        }

        fun build() = BusInfoData(index, name, time, direction, before, after)
    }
}

object BusInfoDatabase {

    private var database : ArrayList<BusInfoData> = ArrayList()

    fun add(data: BusInfoData) : Int {
        database.add(data)
        return database.lastIndex
    }

    fun get(index: Int) : BusInfoData = database[index]

    fun count() : Int = database.size

    fun getIndexByName(start: Int, name: String) : Int {
        for(i in start until database.size) {
            if(database[i].name.contains(name, false))
                return i
        }
        return -1
    }

    fun clear() {
        database.clear()
    }

    fun clone() = database.clone() as ArrayList<BusInfoData>
}