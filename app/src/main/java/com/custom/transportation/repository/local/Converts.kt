package com.custom.transportation.repository.local

import androidx.room.TypeConverter
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.repository.BusStopData

class Converts {

    @TypeConverter
    fun formStation(data: String?) : BusStopData? {
        data ?: return null
        val splits = data.split('/')
        return if(splits.size != 5)  null
        else BusStopData(splits[0], splits[1], splits[2], splits[3].toFloat(), splits[4].toFloat())
    }

    @TypeConverter
    fun busStopDataToStation(data: BusStopData?) : String? {
        data ?: return null
        return "${data.arsId}/${data.stId}/${data.stNm}/${data.tmX}/${data.tmY}"
    }

    @TypeConverter
    fun formBusInfo(data: String?) : BusInfoData? {
        data ?: return null
        val splits = data.split('/')
        return if(splits.size != 7)  null
        else
            BusInfoData(splits[0], splits[1], splits[2],
                ConvertUtil.fromBusType(splits[3]), splits[4], splits[5], ConvertUtil.fromRouteType(splits[6]))
    }

    @TypeConverter
    fun busInfoDataToBusInfo(data: BusInfoData?) : String? {
        data ?: return null
        return "${data.name}/${data.time}/${data.direction}/${ConvertUtil.toBusType(data.thisType)}" +
                "/${data.thisCount}/${data.after}/${ConvertUtil.toRouteType(data.routeType)}"
    }
}