package com.custom.transportation.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.unit.BookmarkData
import com.custom.transportation.data.unit.BookmarkDatabase
import com.custom.transportation.data.unit.BusInfoData
import com.custom.transportation.data.unit.BusStopData

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private val items = ArrayList<Any>()

    fun syncItems() {
        items.clear()
        items.addAll(BookmarkDatabase.getAll())
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
            when(it) {
                is BusStopData -> {
                    holder.tvTitle.text = it.stNm
                    holder.tvSub.text = it.arsId.toString()
                }
                is BusInfoData -> {
                    holder.tvTitle.text = it.name
                    holder.tvSub.text = it.direction
                }
                else -> {
                    holder.tvTitle.text = ""
                    holder.tvSub.text = ""
                }
            }
        }
    }

}
