package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel

class BookmarkPresenter : BookmarkContract.Presenter {

    private val model = BookmarkModel()

    override fun getData(): List<Any> = model.getBookmarkData()

    override fun search(search: String) = Unit

    override fun addBookmark(bookmark: Any) = Unit
}