package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkDataSource
import com.custom.transportation.repository.BookmarkDataSourceImpl
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.repository.BusInfoDataSourceImpl
import com.custom.transportation.repository.mapper.BusInfoMapperImpl

class BusInfoPresenter(val view: BusInfoContract.View) : BusInfoContract.Presenter, BusInfoContract.RemoteCallback {

    private val busInfo = BusInfoDataSourceImpl.INSTANCE
    private val bookmark : BookmarkDataSource = BookmarkDataSourceImpl.INSTANCE

    override fun search(search: String) = busInfo.search(search, this)

    override fun getData(): List<BusInfoData> = busInfo.getAll()

    override fun addBookmark(bookmark: BusInfoData) : Boolean = this.bookmark.insert(BusInfoMapperImpl.toBookmark(data = bookmark))

    override fun deleteBookmark(bookmark: BusInfoData): Boolean = this.bookmark.delete(BusInfoMapperImpl.toBookmark(data = bookmark))

    override fun existBookmark(bookmark: BusInfoData): Int = this.bookmark.isExist(BusInfoMapperImpl.toBookmark(data = bookmark))

    override fun onSuccess() = view.searchSuccess()

    override fun onFailure(msg: String) = view.searchFailure(msg)
}
