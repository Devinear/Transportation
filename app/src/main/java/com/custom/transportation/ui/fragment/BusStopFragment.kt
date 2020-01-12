package com.custom.transportation.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.ui.adapter.BusStopAdapter

class BusStopFragment : TabFragment() {

    private var busStopAdapter = BusStopAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_busstop, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = busStopAdapter

        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.busStop)

    override val fabClickListener: View.OnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Bus Stop")
            val edit = EditText(context)
            edit.filters = Array<InputFilter>(1) {object : InputFilter {
                override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
                    if(source != null && source.matches("^[0-9-]".toRegex()))
                        return source
                    else
                        return ""
                }
            }}
            builder.setView(edit)
            builder.setPositiveButton(context!!.getString(android.R.string.ok), { dialog: DialogInterface?, which:Int ->

            })
//          builder.setNegativeButton(context!!.getString(android.R.string.cancel), {dialog: DialogInterface?, which: Int -> })
            builder.create().apply { show() }
        }
    }

    override fun getDrawable(context: Context): Drawable? = null

    companion object {
        private var instance : BusStopFragment? = null
        @JvmStatic fun getInstance() : BusStopFragment = instance ?: synchronized(this) {
            instance ?: BusStopFragment().also { instance = it }
        }
    }
}