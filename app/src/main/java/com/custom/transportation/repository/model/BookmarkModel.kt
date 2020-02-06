package com.custom.transportation.repository.model

import com.custom.transportation.repository.unit.BookmarkDatabase

class BookmarkModel {

    fun getBookmarkData() : List<Any> = BookmarkDatabase.getAll()

}