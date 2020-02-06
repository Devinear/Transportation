package com.custom.transportation.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.repository.unit.BusInfoData
import com.custom.transportation.repository.unit.BusStopData

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private val items = mutableListOf<Any>()

    fun addItems(items : List<Any>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvSub  : TextView = view.findViewById(R.id.tv_sub)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent,false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            with(holder) {
                when(it) {
                    is BusStopData -> {
                        tvTitle.text = it.stNm
                        tvSub.text = it.arsId.toString()
                    }
                    is BusInfoData -> {
                        tvTitle.text = it.name
                        tvSub.text = it.direction
                    }
                    else -> {
                        tvTitle.text = ""
                        tvSub.text = ""
                    }
                }
            }
        }
    }

}
