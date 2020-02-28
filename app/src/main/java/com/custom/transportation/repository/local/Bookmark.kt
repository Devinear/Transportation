package com.custom.transportation.repository.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.custom.transportation.repository.BookmarkData

@Entity(tableName = "bookmarks")
data class Bookmark(@PrimaryKey/*(autoGenerate = true)*/
                    val id: Int,
                    val data: BookmarkData)