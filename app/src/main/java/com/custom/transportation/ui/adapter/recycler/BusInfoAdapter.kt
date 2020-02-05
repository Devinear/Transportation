package com.custom.transportation.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusInfoData
import com.custom.transportation.presenter.BusStopDetail
import com.google.android.material.snackbar.Snackbar

class BusInfoAdapter(val presenter: BusStopDetail.Presenter) : RecyclerView.Adapter<BusInfoAdapter.ViewHolder>() {
    private val items = mutableListOf<BusInfoData>()

    fun addItems(items : List<BusInfoData>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnLongClickListener {
                presenter.addBookmark(items[adapterPosition])
                Snackbar.make(it, it.context.getText(R.string.add_bookmark), Snackbar.LENGTH_SHORT).show()
                true
            }
        }
        val tvName: TextView    = view.findViewById(R.id.tv_name)
        val tvTime: TextView    = view.findViewById(R.id.tv_time)
        val tvDirection: TextView = view.findViewById(R.id.tv_direction)
        val tvBefore: TextView  = view.findViewById(R.id.tv_before)
        val tvAfter: TextView   = view.findViewById(R.id.tv_after)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bus_info, parent,false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            tvName.text      = items[position].name
            tvTime.text      = items[position].time
            tvDirection.text = items[position].direction
            tvBefore.text    = items[position].before
            tvAfter.text     = items[position].after
        }
    }
}
