package com.custom.transportation.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.repository.BusStopData

@Entity(tableName = "bookmarks")
data class Bookmark(@PrimaryKey/*(autoGenerate = true)*/
                    val id: Int,
                    val isBusStop: Boolean,
                    val station: BusStopData?,
                    val busInfo: BusInfoData?)