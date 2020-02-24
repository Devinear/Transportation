package com.custom.transportation.repository.local

import androidx.room.TypeConverter
import com.custom.transportation.repository.BookmarkData

class Converts {

    @TypeConverter
    fun formData(data: String?) : BookmarkData? {
        data ?: return null
        val splits = data.split('/')
        return if(splits.size != 5)  null
        else BookmarkData(splits[0].toInt(), splits[1].toBoolean(), splits[2], splits[3], splits[4])
    }

    @TypeConverter
    fun bookmarkDataToData(data: BookmarkData?) : String? {
        data ?: return null
        return "${data.key}/${data.isBusInfo}/${data.name}/${data.firValue}/${data.secValue}"
    }
}