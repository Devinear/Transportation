package com.custom.transportation.repository.local

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(vararg bookmark: Bookmark)

    @Delete
    fun deleteBookmark(vararg bookmark: Bookmark)

    @Query("SELECT * FROM bookmarks WHERE id BETWEEN :minIndex AND :maxIndex")
    fun getBetweenIndex(minIndex: Int, maxIndex: Int) : List<Bookmark>

    @Query("SELECT * FROM bookmarks")
    fun getAll(): List<Bookmark>

    @Query("DELETE FROM bookmarks")
    fun deleteAll()

    @Update
    fun update(vararg bookmark: Bookmark)
}