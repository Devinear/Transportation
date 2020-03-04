package com.custom.transportation.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [(Bookmark::class)], version = 3)
@TypeConverters(Converts::class)
abstract class BookmarkDB : RoomDatabase() {

    abstract fun bookmarkDao() : BookmarkDao

//    companion object {
//        private var instance: BookmarkDB? = null
//        fun getInstance(context: Context) : BookmarkDB = instance ?: synchronized(this) {
//            instance ?: Room.databaseBuilder(context.applicationContext, BookmarkDB::class.java, "bookmarks.db")
//                .fallbackToDestructiveMigration()
//                .build()
//                .also { instance = it }
//        }
//    }
}