package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel

class BookmarkPresenter(val view: BookmarkContract.View) : BookmarkContract.Presenter {

    override fun requestData() = BookmarkModel.getInstance(this).reloadData()

    override fun updateBookmark() = view.updateData()

    override fun getData(): List<Any> = BookmarkModel.getInstance(this).getBookmarkAll()

    override fun search(search: String) = Unit

    override fun addBookmark(bookmark: Any) = Unit

    override fun deleteBookmark(bookmark: Any) : Boolean = BookmarkModel.getInstance(this).deleteBookmark(bookmark)
}