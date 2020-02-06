package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel

class BookmarkPresenter : BookmarkContract.Presenter {

    private val model = BookmarkModel()

    override fun getBookmarkData(): List<Any> = model.getBookmarkData()
}