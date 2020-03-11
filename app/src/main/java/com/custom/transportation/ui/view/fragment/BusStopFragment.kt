package com.custom.transportation.ui.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.ui.base.BaseFragment
import com.custom.transportation.ui.adapter.recycler.BusStopAdapter
import com.custom.transportation.ui.contract.BusStopContract
import com.custom.transportation.ui.contract.BusStopPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BusStopFragment : BaseFragment(), BusStopContract.View {

    private val presenter: BusStopContract.Presenter = BusStopPresenter(this)
    private val busStopAdapter = BusStopAdapter(presenter)
    private lateinit var tvEmpty : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_bus_stop, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busStopAdapter
        }

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle(context.getString(R.string.msg_search_bus_stop))
                val edit = EditText(context)
                setView(edit)
                setPositiveButton(context.getString(android.R.string.ok)) { _: DialogInterface?, _: Int ->
                    presenter.run { search(search = edit.text.toString()) }
                }
            }.create().run { show() }
        }
        tvEmpty = view.findViewById(R.id.tv_empty)
        tvEmpty.visibility = View.VISIBLE
        return view
    }

    override fun showFragment() = Unit

    override fun getTitle(context: Context) : String = context.getString(R.string.bus_stop)

    override fun searchSuccess() {
        busStopAdapter.addItems(presenter.getData())
        tvEmpty.visibility = if(busStopAdapter.itemCount == 0) View.VISIBLE else View.GONE
    }

    override fun searchFailure(msg: String)
            = Toast.makeText(context, "Failure:${msg}", Toast.LENGTH_SHORT).show()

    companion object {
        val INSTANCE : BusStopFragment by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BusStopFragment()
        }
//        private var instance : BusStopFragment? = null
//        fun getInstance() : BusStopFragment = instance ?: synchronized(this) {
//            instance ?: BusStopFragment().also { instance = it }
//        }
    }
}