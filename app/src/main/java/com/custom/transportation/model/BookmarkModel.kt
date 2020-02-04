package com.custom.transportation.model

import com.custom.transportation.data.unit.BookmarkDatabase

class BookmarkModel {

    fun getBookmarkData() : List<Any> = BookmarkDatabase.getAll()

}