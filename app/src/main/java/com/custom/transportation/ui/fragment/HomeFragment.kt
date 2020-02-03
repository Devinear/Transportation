package com.custom.transportation.ui.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.presenter.BookmarkPresenter
import com.custom.transportation.ui.adapter.recycler.BookmarkAdapter

class HomeFragment : TabFragment() {

    private val bookmarkAdapter = BookmarkAdapter()
    private val presenter = BookmarkPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            bookmarkAdapter.addItems(presenter.getBookmarkData())
            adapter = bookmarkAdapter
        }
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.title_star)

    override fun getDrawable(context: Context): Drawable? = context.getDrawable(android.R.drawable.ic_dialog_dialer)

    override val fabClickListener = View.OnClickListener { }

    fun showFragment() = bookmarkAdapter.addItems(presenter.getBookmarkData())

    companion object {
        private var instance : HomeFragment? = null
        fun getInstance() : HomeFragment = instance ?: synchronized(this) {
            instance ?: HomeFragment().also { instance = it }
        }
    }
}