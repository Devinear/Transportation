package com.custom.transportation.ui.adapter.recycler

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.common.RouteType
import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.ui.contract.BookmarkPresenter
import com.custom.transportation.ui.view.fragment.TagDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class BookmarkAdapter(val presenter: BookmarkPresenter, private val dragListener: OnDragListener) : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>(), OnItemMoveListener{

    companion object {
        private const val TAG = "BookmarkAdapter"
    }

    private val items = mutableListOf<BookmarkData>()

    fun addItems(items : List<BookmarkData>) {
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

                if(context is AppCompatActivity) {
                    TagDialogFragment().show(context.supportFragmentManager, TAG)
                }

//                AlertDialog.Builder(context).apply {
//                    setMessage(context.getText(R.string.del_bookmark))
//                    setPositiveButton(context.getString(android.R.string.ok)) { dialog: DialogInterface?, _:Int ->
//                        if(items.size <= adapterPosition) {
//                            dialog?.dismiss()
//                            return@setPositiveButton
//                        }
//                        if(presenter.deleteBookmark(items[adapterPosition])) {
//                            Toast.makeText(context, context.getText(R.string.del_bookmark_success), Toast.LENGTH_SHORT).show()
//                            addItems(presenter.getData())
//                        }
//                        else {
//                            Toast.makeText(context, context.getText(R.string.del_bookmark_fail), Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                    setNegativeButton(context.getString(android.R.string.cancel)) { dialog: DialogInterface?, _:Int ->
//                        dialog?.dismiss()
//                    }
//                }.create().run { show() }
            }
            itemView.setOnLongClickListener {
                TagDialogFragment().showsDialog
                false
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
                when(it.isBusInfo) {
                    false -> {
                        vFront.setBackgroundColor(context.getColor(R.color.stop_common))
                        vBack.setBackgroundColor(context.getColor(R.color.stop_common))
                        ivIcon.setImageResource(R.drawable.ic_bus_stop)
                        laStop.visibility = View.VISIBLE
                        laInfo.visibility = View.GONE
                        tvStopTitle.text  = it.name
                        tvStopSub.text    = "${it.secValue.substring(0,2)}-${it.secValue.substring(2)}"
                    }
                    true -> {
                        val route: RouteType = ConvertUtil.fromRouteType(it.secValue)
                        vFront.setBackgroundColor(context.getColor(route.colorId))
                        vBack.setBackgroundColor(context.getColor(route.colorId))
                        ivIcon.setImageResource(R.drawable.ic_directions_bus_2x)
                        laStop.visibility = View.GONE
                        laInfo.visibility = View.VISIBLE
                        tvInfoTitle.text  = it.name
                        tvInfoSub.text    = "[${context.getText(route.nameId)}]"
                    }
                }
            }
        }

        holder.itemView.setOnTouchListener { _, event ->
            if(event.action == MotionEvent.ACTION_DOWN)
                dragListener.onStartDrag(holder)
            return@setOnTouchListener false
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemIdle(fromPosition: Int, toPosition: Int) {
        Log.d("BookmarkAdapter", "onItemIdle from:${fromPosition} to:${toPosition}")
        if(fromPosition == toPosition)  return
        CoroutineScope(Dispatchers.IO).launch {
            presenter.moveBookmark(fromPosition, toPosition)
        }
    }
}