package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.repository.BookmarkDataSourceImpl

class BookmarkPresenter(val view: BookmarkContract.View) : BookmarkContract.Presenter {

    private val bookmark = BookmarkDataSourceImpl.INSTANCE

    override fun getData(): List<BookmarkData> = bookmark.getAll()

    override fun search(search: String) = Unit

    override fun addBookmark(bookmark: BookmarkData) : Boolean = false

    override fun existBookmark(bookmark: BookmarkData): Int = this.bookmark.isExist(bookmark)

    override fun deleteBookmark(bookmark: BookmarkData) : Boolean = this.bookmark.delete(bookmark)

    override fun updateTag(bookmark: BookmarkData) : Boolean = this.bookmark.update(bookmark)

    override suspend fun moveBookmark(fromIndex: Int, toIndex: Int) = bookmark.move(fromIndex, toIndex)

    override suspend fun requestData() = bookmark.reloadData()
}
