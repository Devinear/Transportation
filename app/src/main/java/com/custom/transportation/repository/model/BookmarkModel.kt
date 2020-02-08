package com.custom.transportation.repository.model

class BookmarkModel {

    private val bookmarks : ArrayList<Any> = ArrayList()

    fun getBookmarkAll() : List<Any> = bookmarks

    fun addBookmark(bookmark : Any) {
        if(!isDuplicate(bookmark))
            bookmarks.add(bookmark)
    }

    private fun isDuplicate(bookmark: Any) : Boolean {
        if(bookmarks.size == 0) return false
        for(item in bookmarks) {
            if(item == bookmark) return true
        }
        return false
    }

    companion object {
        private var instance : BookmarkModel? = null
        fun getInstance() : BookmarkModel = instance?: synchronized(this) {
            instance ?: BookmarkModel().also { instance = it }
        }
    }
}