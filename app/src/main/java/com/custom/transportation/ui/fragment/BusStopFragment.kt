package com.custom.transportation.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.VolleyHelper
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.ui.adapter.recycler.BusStopAdapter
import com.custom.transportation.ui.common.ParserListener

class BusStopFragment : TabFragment(), ParserListener {

    private var busStopAdapter = BusStopAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_busstop, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busStopAdapter
        }
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.bus_stop)

    override val fabClickListener: View.OnClickListener = View.OnClickListener {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.search_bus_stop))
            val edit = EditText(context)
            setView(edit)
            setPositiveButton(context!!.getString(android.R.string.ok)) { dialog: DialogInterface?, which: Int ->
                BusStopDatabase.clear()
                VolleyHelper.getInstance(context!!)
                    .requestByName(edit.text.toString(), this@BusStopFragment)
            }
        }.create().run { show() }
    }

    override fun getDrawable(context: Context): Drawable? = null

    companion object {
        private var instance : BusStopFragment? = null
        fun getInstance() : BusStopFragment = instance ?: synchronized(this) {
            instance ?: BusStopFragment().also { instance = it }
        }
    }

    override fun onParserSuccess() {
        busStopAdapter.syncItems()
        busStopAdapter.notifyDataSetChanged()
    }

    override fun onParserFail() = Toast.makeText(context, "PARSER FAIL!", Toast.LENGTH_SHORT).show()
}