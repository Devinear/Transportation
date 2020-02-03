package com.custom.transportation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.data.retrofit.RetrofitHelper
import com.custom.transportation.data.retrofit.ServiceResult
import com.custom.transportation.data.unit.BusInfoDatabase
import com.custom.transportation.ui.adapter.recycler.BusInfoAdapter
import com.custom.transportation.ui.common.Common
import com.custom.transportation.ui.common.IntentType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusStopActivity : AppCompatActivity(), Callback<ServiceResult> {

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
            RetrofitHelper.getRetrofit(Common.baseUrl)
                .getStationByUid(Common.ServiceKey, arsId.toString())
                .enqueue(this@BusStopActivity)
        }
    }

    override fun onFailure(call: Call<ServiceResult>, t: Throwable)
            = Toast.makeText(baseContext, "Failure:["+t.message+"]", Toast.LENGTH_SHORT).show()

    override fun onResponse(call: Call<ServiceResult>, response: Response<ServiceResult>) {

        if(!response.isSuccessful) return

        response.body()?.msgHeader?.run {
            if(headerCd.toInt() != 0) {
                Toast.makeText(baseContext, headerMsg, Toast.LENGTH_SHORT).show()
                return@run
            }
        }

        response.body()?.msgBody?.run {
            BusInfoDatabase.clear()
            for(item in itemList) {
                BusInfoDatabase.add(item.rtNm, item.arrmsg1, item.arrmsg2, item.adirection, item.busType1)
            }
            busInfoAdapter.syncItems()
            busInfoAdapter.notifyDataSetChanged()
        }

    }
}