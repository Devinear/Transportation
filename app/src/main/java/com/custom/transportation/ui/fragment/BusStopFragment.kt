package com.custom.transportation.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.retrofit.RetrofitHelper
import com.custom.transportation.data.retrofit.ServiceResult
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.ui.adapter.recycler.BusStopAdapter
import com.custom.transportation.ui.common.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusStopFragment : TabFragment(), Callback<ServiceResult> {

    private var busStopAdapter = BusStopAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_busstop, container, false)
        view.findViewById<RecyclerView>(R.id.recycler).run {
            layoutManager = LinearLayoutManager(context)
            adapter = busStopAdapter
        }
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.bus_stop)

    override val fabClickListener: View.OnClickListener = View.OnClickListener {
        AlertDialog.Builder(context).apply {
            setTitle(context.getString(R.string.search_bus_stop))
            val edit = EditText(context)
            setView(edit)
            setPositiveButton(context!!.getString(android.R.string.ok)) { dialog: DialogInterface?, which: Int ->
                BusStopDatabase.clear()
                RetrofitHelper.getRetrofit(Common.baseUrl)
                    .getStationByName(Common.ServiceKey, edit.text.toString())
                    .enqueue(this@BusStopFragment)
            }
        }.create().run { show() }
    }

    override fun getDrawable(context: Context): Drawable? = null

    companion object {
        private var instance : BusStopFragment? = null
        fun getInstance() : BusStopFragment = instance ?: synchronized(this) {
            instance ?: BusStopFragment().also { instance = it }
        }
    }

    override fun onFailure(call: Call<ServiceResult>, t: Throwable)
            = Toast.makeText(context, "Failure:["+t.message+"]", Toast.LENGTH_SHORT).show()

    override fun onResponse(call: Call<ServiceResult>, response: Response<ServiceResult>) {
        if(!response.isSuccessful) return

        response.body()?.msgHeader?.run {
            if(headerCd.toInt() != 0) {
                Toast.makeText(context, headerMsg, Toast.LENGTH_SHORT).show()
                return@run
            }
        }

        response.body()?.msgBody?.run {
            BusStopDatabase.clear()
            for(item in itemList) {
                BusStopDatabase.add(item.arsId.toInt(),item.stId.toInt(),item.stNm,item.tmX.toFloat(),item.tmY.toFloat())
            }
            busStopAdapter.syncItems()
            busStopAdapter.notifyDataSetChanged()
        }
    }
}