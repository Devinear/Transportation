package com.custom.transportation.ui.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.ui.contract.BusInfoContract
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class BusInfoAdapter(val presenter: BusInfoContract.Presenter) : RecyclerView.Adapter<BusInfoAdapter.ViewHolder>() {

//    private val items = mutableListOf<BusInfoData>()
//    fun addItems(items : List<BusInfoData>) {
//        with(this.items) {
//            clear()
//            addAll(items)
//        }
//        notifyDataSetChanged()
//    }

    private val lives = mutableListOf<LiveData<BusInfoData>>()
    fun addLiveItems(items: List<LiveData<BusInfoData>>) {
        with(this.lives) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.setOnLongClickListener {
                val data : BusInfoData = lives[adapterPosition].value!!
                if(presenter.existBookmark(data) > -1) {
                    Snackbar.make(it, it.context.getText(R.string.msg_bookmark_exist), Snackbar.LENGTH_SHORT).show()
                }
                else {
                    if(presenter.addBookmark(data)) {
                        Snackbar.make(it, it.context.getText(R.string.msg_bookmark_add), Snackbar.LENGTH_SHORT)
                            .setAction(R.string.cancel) { }
                            .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                                override fun onDismissed(transientBottomBar: Snackbar?, event: Int ) {
                                    if (event == DISMISS_EVENT_ACTION)
                                        presenter.deleteBookmark(data)
                                }
                            }).show()
                    } else {
                        Snackbar.make(it, it.context.getText(R.string.msg_bookmark_add_fail), Snackbar.LENGTH_SHORT).show()
                    }
                }
                true
            }
        }
        val viewFront: View        = view.findViewById(R.id.view_front)
        val viewBack: View        = view.findViewById(R.id.view_back)
        val tvName: TextView      = view.findViewById(R.id.tv_name)
        val tvTime: TextView      = view.findViewById(R.id.tv_time)
        val tvDirection: TextView = view.findViewById(R.id.tv_direction)
        val tvThisType: TextView  = view.findViewById(R.id.tv_this_type)
        val tvThisCount: TextView = view.findViewById(R.id.tv_this_count)
        val tvAfter: TextView     = view.findViewById(R.id.tv_after)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.context, LayoutInflater.from(parent.context).inflate(R.layout.item_bus_info, parent,false))

    override fun getItemCount(): Int  = lives.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            val data : BusInfoData = lives[position].value!!
            viewFront.setBackgroundColor(context.getColor(data.routeType.colorId))
            viewBack.setBackgroundColor(context.getColor(data.routeType.colorId))
            tvName.text      = data.name
            tvTime.text      = data.time
            tvDirection.text = data.direction
            tvThisType.text  = context.getText(data.thisType.typeId)
            tvAfter.text     = data.after
            tvThisCount.text = data.thisCount
        }
    }
}
