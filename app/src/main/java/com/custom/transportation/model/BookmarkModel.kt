package com.custom.transportation.model

import com.custom.transportation.data.unit.BookmarkDatabase

class BookmarkModel {

    fun getBookmarkData() : ArrayList<Any> = BookmarkDatabase.getAll()

}