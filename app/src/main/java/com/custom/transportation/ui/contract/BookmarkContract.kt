package com.custom.transportation.ui.contract

import com.custom.transportation.base.BaseContract

interface BookmarkContract {

    interface View : BaseContract.View {
        fun updateData()
    }

    interface Presenter : BaseContract.Presenter {
        suspend fun requestData()
        fun deleteBookmark(bookmark: Any): Boolean
        suspend fun moveBookmark(fromIndex: Int, toIndex: Int)
        fun getData(): List<Any>
    }
}