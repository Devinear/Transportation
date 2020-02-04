package com.custom.transportation.ui.adapter.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.BusStopDetailActivity
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.presenter.BusStop
import com.custom.transportation.ui.common.IntentType

class BusStopAdapter(val presenter: BusStop.Presenter) : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {
    private val items = mutableListOf<BusStopData>()

    fun addItems(items : List<BusStopData>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener{
                it.context.startActivity(
                    Intent(it.context, BusStopDetailActivity::class.java)
                    .apply { putExtra(IntentType.ArsID.tpye, items[adapterPosition].arsId) })
            }
            view.setOnLongClickListener {
                presenter.addBookmark(items[adapterPosition])
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
        with(holder) {
            tvLocation.text = items[position].stNm
            tvNumber.text = items[position].arsId.toString()
        }
    }

}
