package com.custom.transportation.ui.contract

import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.ui.base.BaseContract

interface BookmarkContract {

    interface View : BaseContract.View {
        fun updateData()
    }

    interface Presenter : BaseContract.Presenter<BookmarkData> {
        suspend fun requestData()
        suspend fun moveBookmark(fromIndex: Int, toIndex: Int)
        fun deleteBookmark(bookmark: BookmarkData): Boolean
    }
}