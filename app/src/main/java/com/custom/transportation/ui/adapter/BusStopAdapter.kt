package com.custom.transportation.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.BusStopActivity
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.ui.common.IntentType

class BusStopAdapter : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {
    var items = ArrayList<BusStopData>()

    init { syncItems() }

    fun syncItems() {
        if(BusStopDatabase.count() > 0) {
            items = BusStopDatabase.clone()
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if(v == null) return
                    val position = adapterPosition
                    items[position].arsId
                    val intent = Intent(v.context, BusStopActivity::class.java)
                    intent.putExtra(IntentType.ArsID.tpye, items[position].arsId)
                    v.context.startActivity(intent)
                }
            })
        }
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
        val tvNumber: TextView = view.findViewById(R.id.tv_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_busstop, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvLocation.text = items[position].stNm
        holder.tvNumber.text = items[position].arsId.toString()
    }

}
