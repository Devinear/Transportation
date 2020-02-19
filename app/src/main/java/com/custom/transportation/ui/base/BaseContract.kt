package com.custom.transportation.ui.base

interface BaseContract {
    interface View {
        fun searchSuccess()
        fun searchFailure(msg : String)
    }

    interface Presenter {
        fun search(search : String)
        fun addBookmark(bookmark: Any)
    }

    interface RemoteCallback {
        fun onSuccess()
        fun onFailure(msg: String)
    }
}