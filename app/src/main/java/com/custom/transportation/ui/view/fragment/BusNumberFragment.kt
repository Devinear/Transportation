package com.custom.transportation.ui.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.custom.transportation.R
import com.custom.transportation.base.BaseFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BusNumberFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_bus_num, container, false)

        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            AlertDialog.Builder(context).apply {
                setTitle(context.getString(R.string.search_bus_number))
                setView(EditText(context).apply {
                    filters = Array(1) {
                        InputFilter { source, start, end, dest, dstart, dend ->
                            if (source != null && source.matches("^[0-9-]".toRegex()))
                                source
                            else
                                ""
                        }
                    }
                })
                setPositiveButton(context.getString(android.R.string.ok)) { dialog: DialogInterface?, which:Int -> }
            }.create().run { show() }
        }
        return view
    }

    override fun showFragment() = Unit

    override fun getTitle(context: Context) : String = context.getString(R.string.bus_number)

    companion object {
        private var instance : BusNumberFragment? = null
        fun getInstance() : BusNumberFragment = instance ?: synchronized(this) {
            instance ?: BusNumberFragment().also { instance = it }
        }
    }
}