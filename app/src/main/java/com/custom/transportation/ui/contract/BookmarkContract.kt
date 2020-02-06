package com.custom.transportation.ui.contract

import com.custom.transportation.repository.model.BookmarkModel

interface BookmarkContract {

    interface Presenter {
        fun getBookmarkData() : List<Any>
    }
}