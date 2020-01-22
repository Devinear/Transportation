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
    var items = ArrayList<Any>()

    fun syncItems() {
        items.clear()
        (0 until BookmarkDatabase.count()).forEach { i -> items.add(BookmarkDatabase.get(i)) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvSub  : TextView = view.findViewById(R.id.tv_sub)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent,false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        var title = ""
        var sub   = ""
        if(item is BusStopData) {
            title = item.stNm
            sub   = item.arsId.toString()
        }
        else if(item is BusInfoData) {
            title = item.name
            sub   = item.direction
        }
        holder.tvTitle.text = title
        holder.tvSub.text   = sub
    }

}
