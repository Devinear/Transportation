package com.custom.transportation.presenter

import com.custom.transportation.model.BookmarkModel

interface Bookmark {

    interface Presenter {
        fun getBookmarkData() : ArrayList<Any>
    }
}

class BookmarkPresenter : Bookmark.Presenter {

    private val model = BookmarkModel()

    override fun getBookmarkData(): ArrayList<Any> = model.getBookmarkData()
}