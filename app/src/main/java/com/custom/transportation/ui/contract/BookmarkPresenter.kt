package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkDataSourceImpl

class BookmarkPresenter(val view: BookmarkContract.View) : BookmarkContract.Presenter {

    private val bookmark = BookmarkDataSourceImpl.INSTANCE

    override fun getData(): List<Any> = bookmark.getAll()

    override fun search(search: String) = Unit

    override fun addBookmark(bookmark: Any) = Unit

    override fun deleteBookmark(bookmark: Any) : Boolean = this.bookmark.delete(bookmark)

    override suspend fun moveBookmark(fromIndex: Int, toIndex: Int) = bookmark.move(fromIndex, toIndex)

    override suspend fun requestData() = bookmark.reloadData()
}