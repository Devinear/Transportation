package com.custom.transportation.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.BusStopDetailActivity
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.ui.adapter.recycler.BusStopAdapter
import com.custom.transportation.ui.common.IntentType
import com.custom.transportation.presenter.BusStop
import com.custom.transportation.presenter.BusStopPresenter

class BusStopFragment : TabFragment(), BusStop.View {

    private val busStopAdapter = BusStopAdapter(this)
    private val presenter: BusStop.Presenter = BusStopPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_busstop, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busStopAdapter
        }
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.bus_stop)

    override fun getDrawable(context: Context): Drawable? = null

    override val fabClickListener: View.OnClickListener = View.OnClickListener {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.search_bus_stop))
            val edit = EditText(context)
            setView(edit)
            setPositiveButton(context.getString(android.R.string.ok)) { _: DialogInterface?, _: Int ->
                presenter.searchWord(edit.text.toString())
            }
        }.create().run { show() }
    }

    override fun searchSuccess(items : ArrayList<BusStopData>) {
        busStopAdapter.addItems(items)
        busStopAdapter.notifyDataSetChanged()
    }

    override fun searchFailure(msg: String)
            = Toast.makeText(context, "Failure:${msg}", Toast.LENGTH_SHORT).show()

    fun onItemClick(arsId: Int) {
        startActivity(Intent(context, BusStopDetailActivity::class.java).apply {
            putExtra(IntentType.ArsID.tpye, arsId)
        })
    }

    fun onItemLongClick(data: BusStopData) {
        presenter.addBookmark(data)
    }

    companion object {
        private var instance : BusStopFragment? = null
        fun getInstance() : BusStopFragment = instance ?: synchronized(this) {
            instance ?: BusStopFragment().also { instance = it }
        }
    }
}