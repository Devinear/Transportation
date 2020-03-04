package com.custom.transportation.common

import android.app.Application
import androidx.room.Room
import com.custom.transportation.repository.local.BookmarkDB

class App : Application() {

    companion object {
        // @Volatile
        private lateinit var database : BookmarkDB

        val DATABASE : BookmarkDB
            get() = database
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this, BookmarkDB::class.java, "bookmarks.db"
        ).build()
    }
}