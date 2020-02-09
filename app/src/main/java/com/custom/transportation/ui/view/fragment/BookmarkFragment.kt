package com.custom.transportation.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.base.BaseFragment
import com.custom.transportation.ui.adapter.recycler.BookmarkAdapter
import com.custom.transportation.ui.contract.BookmarkPresenter

class BookmarkFragment : BaseFragment() {

    private val presenter = BookmarkPresenter()
    private val bookmarkAdapter = BookmarkAdapter(presenter)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            bookmarkAdapter.addItems(presenter.getData())
            adapter = bookmarkAdapter
        }
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.title_star)

    override fun showFragment() = bookmarkAdapter.addItems(presenter.getData())

    companion object {
        private var instance : BookmarkFragment? = null
        fun getInstance() : BookmarkFragment = instance ?: synchronized(this) {
            instance ?: BookmarkFragment().also { instance = it }
        }
    }
}