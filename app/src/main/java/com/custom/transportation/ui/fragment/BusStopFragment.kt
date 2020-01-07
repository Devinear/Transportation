package com.custom.transportation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.ui.adapter.BusStopAdapter

class BusStopFragment : Fragment() {

    private var busStopAdapter = BusStopAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view : View = inflater.inflate(R.layout.fragment_busstop, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = busStopAdapter

        return view
    }

    fun getTitle(context: Context) : String = context.getString(R.string.busStop)

    companion object {
        private var instance : BusStopFragment? = null
        @JvmStatic fun getInstance() : BusStopFragment = instance ?: synchronized(this) {
            instance ?: BusStopFragment().also { instance = it }
        }
    }
}