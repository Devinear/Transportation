package com.custom.transportation.ui.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.custom.transportation.R
import com.custom.transportation.common.IntentType
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.ui.adapter.recycler.BusInfoAdapter
import com.custom.transportation.ui.contract.BusInfoContract
import com.custom.transportation.ui.contract.BusInfoPresenter
import com.custom.transportation.ui.viewModel.BusInfoViewModel

class BusInfoActivity : AppCompatActivity(), BusInfoContract.View {

    private val presenter: BusInfoContract.Presenter = BusInfoPresenter(this)
    private val busInfoAdapter = BusInfoAdapter(presenter)

    private lateinit var swipeLayout: SwipeRefreshLayout

    private lateinit var model: BusInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_info)

//        model = ViewModelProviders.of(this)[BusInfoViewModel::class.java]
//        val observer = Observer<BusInfoData> {
//
//        }
//        model.busInfo.observe(this, observer)

        findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busInfoAdapter
        }

        val arsId : String? = intent.getStringExtra(IntentType.ArsID.type)

        swipeLayout = findViewById(R.id.la_swipe)
        swipeLayout.setOnRefreshListener {
            arsId ?: return@setOnRefreshListener
            presenter.run { search(search = arsId) }
        }

        // 최초 1회 검색 필요
        arsId ?: return
        presenter.run { search(search = arsId) }
    }

    override fun searchSuccess() {
        val lives = presenter.getLiveData()
        val observer = Observer<BusInfoData> {
            busInfoAdapter.notifyDataSetChanged()
        }
        lives.forEach {
            it.observe(this, observer)
        }
        busInfoAdapter.addLiveItems(lives)
//        busInfoAdapter.addItems(presenter.getData())
        swipeLayout.isRefreshing = false
    }

    override fun searchFailure(msg: String) {
        Toast.makeText(this, "Failure:${msg}", Toast.LENGTH_SHORT).show()
        swipeLayout.isRefreshing = false
    }
}