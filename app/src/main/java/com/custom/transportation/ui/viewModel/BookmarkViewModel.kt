package com.custom.transportation.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.custom.transportation.repository.BookmarkData

class BookmarkViewModel : ViewModel() {

    val bookmarks : MutableLiveData<BookmarkData> by lazy {
        MutableLiveData<BookmarkData>()
    }

}