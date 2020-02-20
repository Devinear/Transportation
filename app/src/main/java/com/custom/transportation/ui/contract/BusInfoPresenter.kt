package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkDataSource
import com.custom.transportation.repository.BookmarkDataSourceImpl
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.repository.BusInfoDataSourceImpl

class BusInfoPresenter(val view: BusInfoContract.View) : BusInfoContract.Presenter, BusInfoContract.RemoteCallback {

    private val busInfo = BusInfoDataSourceImpl.getInstance()
    private val bookmark : BookmarkDataSource = BookmarkDataSourceImpl.getInstance()

    override fun search(search: String) = busInfo.search(search, this)

    override fun getData(): List<BusInfoData> = busInfo.getAll()

    override fun addBookmark(bookmark: Any) = this.bookmark.insert(bookmark)

    override fun onSuccess() = view.searchSuccess()

    override fun onFailure(msg: String) = view.searchFailure(msg)
}
