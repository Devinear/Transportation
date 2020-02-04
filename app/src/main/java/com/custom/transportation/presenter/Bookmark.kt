package com.custom.transportation.presenter

import com.custom.transportation.model.BookmarkModel

interface Bookmark {

    interface Presenter {
        fun getBookmarkData() : List<Any>
    }
}

class BookmarkPresenter : Bookmark.Presenter {

    private val model = BookmarkModel()

    override fun getBookmarkData(): List<Any> = model.getBookmarkData()
}