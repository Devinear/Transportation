package com.custom.transportation.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.custom.transportation.repository.model.BusInfoData
import com.custom.transportation.repository.model.BusStopData

@Entity(tableName = "bookmarks")
data class Bookmark(@PrimaryKey(autoGenerate = true)
                    val isBusStop: Boolean,
                    val station: BusStopData?,
                    val busInfo: BusInfoData?)