package com.custom.transportation.ui.base

interface BaseContract {
    interface View {
        fun searchSuccess()
        fun searchFailure(msg : String)
    }

    interface Presenter<T> {
        fun search(search : String)
        fun addBookmark(bookmark: T) : Boolean
        fun existBookmark(bookmark: T) : Boolean
        fun getData() : List<T>
    }

    interface RemoteCallback {
        fun onSuccess()
        fun onFailure(msg: String)
    }
}