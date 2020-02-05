package com.custom.transportation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.custom.transportation.R

class BusNumberFragment : TabFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_subway, container, false)

    override fun getTitle(context: Context) : String = context.getString(R.string.bus_number)

    // 추후 버스 정류장 번호 검색 개발시 참고
//    override val fabClickListener: View.OnClickListener = View.OnClickListener {
//        AlertDialog.Builder(context).apply {
//            setTitle(context.getString(R.string.search_bus_number))
//            setView(EditText(context).apply {
//                filters = Array(1) {
//                    InputFilter { source, start, end, dest, dstart, dend ->
//                        if (source != null && source.matches("^[0-9-]".toRegex()))
//                            source
//                        else
//                            ""
//                    }
//                }
//            })
//            setPositiveButton(context!!.getString(android.R.string.ok)) { dialog: DialogInterface?, which:Int ->
//            }
//        }.create().run { show() }
//    }

    companion object {
        private var instance : BusNumberFragment? = null
        fun getInstance() : BusNumberFragment = instance ?: synchronized(this) {
            instance ?: BusNumberFragment().also { instance = it }
        }
    }
}