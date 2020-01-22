package com.custom.transportation.data.unit

class BusInfoData private constructor
    (val index: Int, val name: String, val time: String, val direction: String, val before: String, val after: String)
{

    class Builder(private val index: Int) {

        // 커스텀 쎄터
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

    private val database : ArrayList<BusInfoData> = ArrayList()

    fun add(data: BusInfoData) : Int {
        database.add(data)
        return database.lastIndex
    }

    fun get(index: Int) : BusInfoData = database[index]

    fun count() : Int = database.size

    fun clear() = database.clear()

    @Suppress("UNCHECKED_CAST")
    fun clone() = database.clone() as ArrayList<BusInfoData>
}