package com.custom.transportation.ui.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.common.IntentType
import com.custom.transportation.ui.adapter.recycler.BusInfoAdapter
import com.custom.transportation.ui.contract.BusInfoContract
import com.custom.transportation.ui.contract.BusInfoPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BusInfoActivity : AppCompatActivity(), BusInfoContract.View {

    private val presenter: BusInfoContract.Presenter = BusInfoPresenter(this)
    private val busInfoAdapter = BusInfoAdapter(presenter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_info)

        findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busInfoAdapter
        }

        val arsId : String? = intent.getStringExtra(IntentType.ArsID.type)

        findViewById<FloatingActionButton>(R.id.fab).apply {
            isEnabled = arsId != null
        }.setOnClickListener {
            arsId ?: return@setOnClickListener
            presenter.run { search(search = arsId) }
        }

        // 최초 1회 검색 필요
        arsId ?: return
        presenter.run { search(search = arsId) }
    }

    override fun searchSuccess() = busInfoAdapter.addItems(presenter.getData())

    override fun searchFailure(msg: String)
            = Toast.makeText(this, "Failure:${msg}", Toast.LENGTH_SHORT).show()
}