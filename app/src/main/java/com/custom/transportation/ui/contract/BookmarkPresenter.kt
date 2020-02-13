package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkDataSource
import com.custom.transportation.repository.BookmarkDataSourceImpl

class BookmarkPresenter(val view: BookmarkContract.View) : BookmarkContract.Presenter, BookmarkContract.Callback {

    private val bookmark: BookmarkDataSource = BookmarkDataSourceImpl.getInstance()

    override fun getData(): List<Any> = bookmark.getAll()

    override fun search(search: String) = Unit

    override fun addBookmark(bookmark: Any) = Unit

    override fun deleteBookmark(bookmark: Any) : Boolean = this.bookmark.delete(bookmark)

    override fun requestData() = bookmark.reloadData(this)

    override fun onComplete() = view.updateData()
}