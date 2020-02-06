package com.custom.transportation.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.repository.unit.BusInfoData
import com.custom.transportation.ui.contract.BusStopDetailContract
import com.custom.transportation.ui.contract.BusStopDetailPresenter
import com.custom.transportation.ui.adapter.recycler.BusInfoAdapter
import com.custom.transportation.common.IntentType

class BusStopDetailActivity : AppCompatActivity(), BusStopDetailContract.View {

    private val presenter: BusStopDetailContract.Presenter = BusStopDetailPresenter(this)
    private val busInfoAdapter = BusInfoAdapter(presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_stop)

        findViewById<RecyclerView>(R.id.recycler_busstop).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busInfoAdapter
        }

        val arsId = intent.getIntExtra(IntentType.ArsID.tpye, -1)
        if(arsId != -1) {
            presenter.searchArsId(arsId)
        }
    }

    override fun searchSuccess(items: List<BusInfoData>) = busInfoAdapter.addItems(items)

    override fun searchFailure(msg: String)
            = Toast.makeText(this, "Failure:${msg}", Toast.LENGTH_SHORT).show()
}