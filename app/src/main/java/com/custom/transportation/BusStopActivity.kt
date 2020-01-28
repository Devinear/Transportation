package com.custom.transportation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.data.VolleyHelper
import com.custom.transportation.ui.adapter.recycler.BusInfoAdapter
import com.custom.transportation.ui.common.IntentType
import com.custom.transportation.ui.common.ParserListener

class BusStopActivity : AppCompatActivity(), ParserListener {

    private val busInfoAdapter = BusInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busstop)

        findViewById<RecyclerView>(R.id.recycler_busstop).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busInfoAdapter
        }

        val arsId = intent.getIntExtra(IntentType.ArsID.tpye, -1)
        if(arsId != -1) {
            VolleyHelper.getInstance(this).cancelAll()
            VolleyHelper.getInstance(this).requestByArsId(arsId, this@BusStopActivity)
        }
    }

    override fun onParserSuccess() {
        busInfoAdapter.syncItems()
        busInfoAdapter.notifyDataSetChanged()
    }

    override fun onParserFail() = Toast.makeText(this, "PARSER FAIL!", Toast.LENGTH_SHORT).show()
}