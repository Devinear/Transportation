package com.custom.transportation.ui.base

import androidx.lifecycle.LiveData

interface BaseContract {
    interface View {
        fun searchSuccess()
        fun searchFailure(msg : String)
    }

    interface Presenter<T> {
        fun search(search : String)
        fun addBookmark(bookmark: T) : Boolean
        fun deleteBookmark(bookmark: T): Boolean
        fun existBookmark(bookmark: T) : Int
        fun getData() : List<T>
    }

    interface RemoteCallback {
        fun onSuccess()
        fun onFailure(msg: String)
    }
}