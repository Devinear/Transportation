package com.custom.transportation.ui.contract

import com.custom.transportation.base.BaseContract

interface BookmarkContract {

    interface View : BaseContract.View {
        fun updateData()
    }

    interface Presenter : BaseContract.Presenter {
        fun requestData()
        fun getData() : List<Any>
        fun deleteBookmark(bookmark: Any) : Boolean
    }
}