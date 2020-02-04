package com.custom.transportation.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.ui.fragment.BusStopFragment

class BusStopAdapter(val fragment: BusStopFragment) : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {
    private val items = mutableListOf<BusStopData>()

    fun addItems(items : List<BusStopData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener{
                fragment.onItemClick(items[adapterPosition].arsId)
            }
            view.setOnLongClickListener {
                fragment.onItemLongClick(items[adapterPosition])
                return@setOnLongClickListener true
            }
        }
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
        val tvNumber: TextView = view.findViewById(R.id.tv_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_busstop, parent,false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvLocation.text = items[position].stNm
        holder.tvNumber.text = items[position].arsId.toString()
    }

}
