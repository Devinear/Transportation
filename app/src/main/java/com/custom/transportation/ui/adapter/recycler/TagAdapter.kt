package com.custom.transportation.ui.adapter.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R

class TagAdapter(tags: String) : RecyclerView.Adapter<TagAdapter.ViewHolder>() {

    val items = mutableListOf<String>()

    init {
        if(tags.isNotEmpty())
            items.addAll(tags.split(", "))
    }

    fun addItem(item: String): Boolean {
        if(items.indexOf(item) > 0) return false

        items.add(item)
        notifyDataSetChanged()
        return true
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTag: TextView = view.findViewById(R.id.tv_tag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tag, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTag.text = items[position]
    }
}