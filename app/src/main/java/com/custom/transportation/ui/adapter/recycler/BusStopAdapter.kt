package com.custom.transportation.ui.adapter.recycler

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.common.IntentType
import com.custom.transportation.repository.BusStopData
import com.custom.transportation.ui.contract.BusStopContract
import com.custom.transportation.ui.view.activity.BusInfoActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class BusStopAdapter(val presenter: BusStopContract.Presenter) : RecyclerView.Adapter<BusStopAdapter.ViewHolder>() {
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
                    Intent(it.context, BusInfoActivity::class.java)
                    .apply { putExtra(IntentType.ArsID.type, items[adapterPosition].arsId) })
            }
            view.setOnLongClickListener {
//                presenter.addBookmark(items[adapterPosition])
                Snackbar.make(it, it.context.getText(R.string.add_bookmark), Snackbar.LENGTH_SHORT)
                    .setAction(R.string.cancel) { }
                    .addCallback(object: BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            if(event != DISMISS_EVENT_ACTION)
                                presenter.addBookmark(items[adapterPosition])
                        }
                    })
                    .show()
                return@setOnLongClickListener true
            }
        }
        val tvLocation: TextView = view.findViewById(R.id.tv_location)
        val tvNumber: TextView = view.findViewById(R.id.tv_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bus_stop, parent,false))

    override fun getItemCount(): Int  = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            tvLocation.text = items[position].stNm
            tvNumber.text = if(items[position].arsId.length >= 5)
                "${items[position].arsId.substring(0,2)}-${items[position].arsId.substring(2)}"
            else items[position].arsId
        }
    }

}
