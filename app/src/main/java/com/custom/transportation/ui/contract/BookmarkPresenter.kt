package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel

class BookmarkPresenter : BookmarkContract.Presenter {

    private val model = BookmarkModel.getInstance()

    override fun getData(): List<Any> = model.getBookmarkAll()

    override fun search(search: String) = Unit

    override fun addBookmark(bookmark: Any) = Unit

    override fun deleteBookmark(bookmark: Any) : Boolean = model.deleteBookmark(bookmark)
}