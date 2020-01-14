package com.custom.transportation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.data.VolleyHelper
import com.custom.transportation.ui.adapter.BusInfoAdapter
import com.custom.transportation.ui.common.ParserListener

class BusStopActivity : AppCompatActivity(), ParserListener {

    private var busInfoAdapter = BusInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busstop)

        val recycler = findViewById<RecyclerView>(R.id.recycler_busstop)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = busInfoAdapter

        val arsId = intent.getIntExtra("BUSSTOPDATA_ARSID", -1)
        if(arsId != -1) {
            VolleyHelper.getInstance(this).cancelAll()
            VolleyHelper.getInstance(this).requestByArsId(arsId, this@BusStopActivity)
        }
    }

    override fun parserFinish(success: Boolean) {
        if(success) {
            busInfoAdapter.syncItems()
            busInfoAdapter.notifyDataSetChanged()
        }
    }
}