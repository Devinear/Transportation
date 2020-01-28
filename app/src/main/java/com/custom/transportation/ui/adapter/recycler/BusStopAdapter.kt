package com.custom.transportation.ui.adapter.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.BusStopActivity
import com.custom.transportation.R
import com.custom.transportation.data.unit.BookmarkDatabase
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.ui.common.IntentType

class BusStopAdapter : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {
    private val items = ArrayList<BusStopData>()

    fun syncItems() {
        items.clear()
        items.addAll(BusStopDatabase.getAll())
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener(View.OnClickListener { v ->
                v ?: return@OnClickListener
                v.context.startActivity(Intent(v.context, BusStopActivity::class.java).apply {
                    putExtra(IntentType.ArsID.tpye, items[adapterPosition].arsId)
                })
            })
            view.setOnLongClickListener {v ->
                BookmarkDatabase.add(items[adapterPosition])
                Toast.makeText(v.context, v.context.getText(R.string.add_bookmark), Toast.LENGTH_SHORT).show()
                true
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
