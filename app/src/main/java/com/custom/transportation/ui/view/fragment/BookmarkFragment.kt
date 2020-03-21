package com.custom.transportation.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.ui.adapter.recycler.BookmarkAdapter
import com.custom.transportation.ui.adapter.recycler.BookmarkTouchHelper
import com.custom.transportation.ui.adapter.recycler.OnDragListener
import com.custom.transportation.ui.base.BaseFragment
import com.custom.transportation.ui.contract.BookmarkContract
import com.custom.transportation.ui.contract.BookmarkPresenter
import com.custom.transportation.ui.viewModel.BookmarkViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkFragment : BaseFragment(), BookmarkContract.View, OnDragListener {

    private val presenter = BookmarkPresenter(this)
    private val bookmarkAdapter = BookmarkAdapter(presenter, this)
    private val bookmarkHelper = ItemTouchHelper(BookmarkTouchHelper(bookmarkAdapter))
    private var tvEmpty : TextView? = null // lateinit 의 경우 CreateView 생성 시점때문

//    private lateinit var viewModelFactory: ViewModelProvider.AndroidViewModelFactory
    private lateinit var viewModel: BookmarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 흠... ViewModelProviders.of는 Deprecated 되었기때문에 변경이 필요하다..
        viewModel = ViewModelProviders.of(this)[BookmarkViewModel::class.java]
        val bookmarkObserver = Observer<BookmarkData> {
            if(!bookmarkAdapter.addItem(it) && view != null)
                Snackbar.make(view!!,getText(R.string.msg_bookmark_add_fail),Snackbar.LENGTH_SHORT).show()
        }
        viewModel.bookmarks.observe(this, bookmarkObserver)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_bookmark, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            bookmarkHelper.attachToRecyclerView(this)
            layoutManager = LinearLayoutManager(context)
            adapter = bookmarkAdapter
        }
        tvEmpty = view.findViewById(R.id.tv_empty)
        updateData()
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.title_star)

    override fun showFragment() = updateData()

    override fun updateData() {
        CoroutineScope(Dispatchers.Main).launch {
            presenter.requestData()
            bookmarkAdapter.addItems(presenter.getData())
            tvEmpty?.visibility = if(bookmarkAdapter.itemCount == 0) View.VISIBLE else View.GONE
        }
    }

    override fun searchSuccess() = Unit

    override fun searchFailure(msg: String) = Unit

    override fun onStartDrag(holder: BookmarkAdapter.ViewHolder) = bookmarkHelper.startDrag(holder)

    companion object {
        val INSTANCE : BookmarkFragment by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BookmarkFragment()
        }
    }
}