package com.custom.transportation.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.data.unit.BookmarkData
import com.custom.transportation.data.unit.BookmarkDatabase

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    var items = ArrayList<BookmarkData>().apply { syncItems() }

    fun syncItems() {
        if(BookmarkDatabase.count() > 0) {

        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val tvName: TextView = view.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_businfo, parent,false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

}
