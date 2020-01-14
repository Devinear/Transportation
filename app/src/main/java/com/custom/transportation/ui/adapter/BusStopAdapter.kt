package com.custom.transportation.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.BusStopActivity
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase

class BusStopAdapter : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {
    var items = ArrayList<BusStopData>()

    init { syncItems() }

    fun syncItems() {
        if(BusStopDatabase.count() > 0) {
            items = BusStopDatabase.clone()
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if(v == null) return
                    val position = adapterPosition
                    items[position].arsId
                    val intent = Intent(v.context, BusStopActivity::class.java)
                    intent.putExtra("BUSSTOPDATA_ARSID", items[position].arsId)
                    v.context.startActivity(intent)
                }
            })
        }

        //val iv = view.findViewById<ImageView>(R.id.iv_icon)
        val tv_location = view.findViewById<TextView>(R.id.tv_location)
        val tv_number = view.findViewById<TextView>(R.id.tv_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_busstop, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv_location.text = items[position].stNm
        holder.tv_number.text = items[position].arsId.toString()
    }

}
