package com.custom.transportation.ui.contract

import com.custom.transportation.repository.*

class BusStopPresenter(val view: BusStopContract.View) : BusStopContract.Presenter, BusStopContract.Callback {

    private val busStop : BusStopDataSource = BusStopDataSourceImpl.getInstance()
    private val bookmark : BookmarkDataSource = BookmarkDataSourceImpl.getInstance()

    override fun search(search: String) = busStop.search(search, this)

    override fun getData(): List<BusStopData> = busStop.getAll()

    override fun addBookmark(bookmark: Any) = this.bookmark.insert(bookmark)

    override fun onSuccess() = view.searchSuccess()

    override fun onFailure(msg: String) = view.searchFailure(msg)
}