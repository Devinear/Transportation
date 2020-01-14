package com.custom.transportation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusInfoData
import com.custom.transportation.data.unit.BusInfoDatabase

class BusInfoAdapter : RecyclerView.Adapter<BusInfoAdapter.ViewHolder>() {
    var items = ArrayList<BusInfoData>()

    init { syncItems() }

    fun syncItems() {
        if(BusInfoDatabase.count() > 0) {
            items = BusInfoDatabase.clone()
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name     = view.findViewById<TextView>(R.id.tv_name)
        val time     = view.findViewById<TextView>(R.id.tv_time)
        val direction= view.findViewById<TextView>(R.id.tv_direction)
        val before   = view.findViewById<TextView>(R.id.tv_before)
        val after    = view.findViewById<TextView>(R.id.tv_after)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_businfo, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text      = items[position].name
        holder.time.text      = items[position].time
        holder.direction.text = items[position].direction
        holder.before.text    = items[position].before
        holder.after.text     = items[position].after
    }

}
