package com.custom.transportation.repository.local

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(vararg bookmark: Bookmark)

    @Delete
    fun deleteBookmark(vararg bookmark: Bookmark)

    @Query("SELECT * FROM bookmarks")
    fun getAll(): List<Bookmark>

    @Query("DELETE FROM bookmarks")
    fun deleteAll()
}