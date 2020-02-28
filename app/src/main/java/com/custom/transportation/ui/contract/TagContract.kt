package com.custom.transportation.ui.contract

interface TagContract {
    interface View {
        fun addTags(position: Int, tags: String)
    }
}