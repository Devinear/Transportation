package com.custom.transportation.ui.adapter.recycler

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.repository.BusInfoData
import com.custom.transportation.repository.BusStopData
import com.custom.transportation.ui.contract.BookmarkPresenter

class BookmarkAdapter(val presenter: BookmarkPresenter) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {
    private val items = mutableListOf<Any>()

    fun addItems(items : List<Any>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val vFront     : View = view.findViewById(R.id.view_front)
        val vBack      : View = view.findViewById(R.id.view_back)
        val ivIcon     : ImageView = view.findViewById(R.id.iv_icon)
        val laStop     : LinearLayout = view.findViewById(R.id.layout_stop_text)
        val tvStopTitle: TextView = view.findViewById(R.id.tv_stop_title)
        val tvStopSub  : TextView = view.findViewById(R.id.tv_stop_sub)
        val laInfo     : LinearLayout = view.findViewById(R.id.layout_info_text)
        val tvInfoTitle: TextView = view.findViewById(R.id.tv_info_title)
        val tvInfoSub  : TextView = view.findViewById(R.id.tv_info_sub)
        private val ibDelete   : ImageButton = view.findViewById(R.id.ib_delete)

        init {
            ibDelete.setOnClickListener{
                AlertDialog.Builder(context).apply {
                    setMessage(context.getText(R.string.del_bookmark))
                    setPositiveButton(context.getString(android.R.string.ok)) { dialog: DialogInterface?, _:Int ->
                        if(items.size <= adapterPosition) {
                            dialog?.dismiss()
                            return@setPositiveButton
                        }
                        if(presenter.deleteBookmark(items[adapterPosition])) {
                            Toast.makeText(context, context.getText(R.string.del_bookmark_success), Toast.LENGTH_SHORT).show()
                            addItems(presenter.getData())
                        }
                        else {
                            Toast.makeText(context, context.getText(R.string.del_bookmark_fail), Toast.LENGTH_SHORT).show()
                        }
                    }
                    setNegativeButton(context.getString(android.R.string.cancel)) { dialog: DialogInterface?, _:Int ->
                        dialog?.dismiss()
                    }
                }.create().run { show() }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.context, LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent,false))

    override fun getItemCount(): Int  = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            with(holder) {
                when(it) {
                    is BusStopData -> {
                        vFront.setBackgroundColor(context.getColor(R.color.stop_common))
                        vBack.setBackgroundColor(context.getColor(R.color.stop_common))
                        ivIcon.setImageResource(R.drawable.ic_bus_stop)
                        laStop.visibility = View.VISIBLE
                        laInfo.visibility = View.GONE
                        tvStopTitle.text  = it.stNm
                        tvStopSub.text    = "${it.arsId.substring(0,2)}-${it.arsId.substring(2)}"
                    }
                    is BusInfoData -> {
                        vFront.setBackgroundColor(context.getColor(it.routeType.colorId))
                        vBack.setBackgroundColor(context.getColor(it.routeType.colorId))
                        ivIcon.setImageResource(R.drawable.ic_directions_bus_2x)
                        laStop.visibility = View.GONE
                        laInfo.visibility = View.VISIBLE
                        tvInfoTitle.text  = it.name
                        tvInfoSub.text    = "[${context.getText(it.routeType.nameId)}]"
                    }
                }
            }
        }
    }

}
