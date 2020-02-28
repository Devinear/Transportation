package com.custom.transportation.repository.local

import androidx.room.TypeConverter
import com.custom.transportation.repository.BookmarkData
import com.google.gson.Gson

class Converts {

    @TypeConverter
    fun formData(data: String?) : BookmarkData? {
        data ?: return null
        return Gson().fromJson(data, BookmarkData::class.java)
    }

    @TypeConverter
    fun bookmarkDataToData(data: BookmarkData?) : String? {
        data ?: return null
        return Gson().toJson(data)
    }
}