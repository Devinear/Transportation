package com.custom.transportation.repository.local

import androidx.room.*

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(vararg bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(vararg bookmark: Bookmark)

    @Query("SELECT * FROM bookmarks WHERE id BETWEEN :minIndex AND :maxIndex")
    suspend fun getBetweenIndex(minIndex: Int, maxIndex: Int) : List<Bookmark>

    @Query("SELECT * FROM bookmarks")
    suspend fun getAll(): List<Bookmark>

    @Query("DELETE FROM bookmarks")
    suspend fun deleteAll()

    @Update
    suspend fun update(vararg bookmark: Bookmark)
}