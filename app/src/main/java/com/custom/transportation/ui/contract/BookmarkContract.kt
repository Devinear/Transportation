package com.custom.transportation.ui.contract

import com.custom.transportation.base.BaseContract

interface BookmarkContract {

    interface Presenter : BaseContract.Presenter {
        fun getData() : List<Any>
    }
}