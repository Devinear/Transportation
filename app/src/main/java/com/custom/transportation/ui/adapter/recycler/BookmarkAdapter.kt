package com.custom.transportation.ui.adapter.recycler

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.custom.transportation.R
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.common.RouteType
import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.ui.contract.BookmarkPresenter
import com.custom.transportation.ui.contract.TagContract
import com.custom.transportation.ui.view.fragment.TagDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class BookmarkAdapter(val presenter: BookmarkPresenter, private val dragListener: OnDragListener)
    : RecyclerView.Adapter<BookmarkAdapter.ViewHolder>(), OnItemMoveListener, TagContract.View
{
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

    fun addItem(item : BookmarkData) : Boolean {
        if(items.indexOf(item) == -1) {
            items.add(item)
            return true
        }
        return false
    }

    inner class ViewHolder(val context: Context, view: View) : RecyclerView.ViewHolder(view) {
        val vFront : View      = view.findViewById(R.id.view_front)
        val vBack  : View      = view.findViewById(R.id.view_back)
        val ivIcon : ImageView = view.findViewById(R.id.iv_icon)
        val tvTitle: TextView  = view.findViewById(R.id.tv_title)
        val tvSub  : TextView  = view.findViewById(R.id.tv_sub)
        val tvTags : TextView  = view.findViewById(R.id.tv_tags)
        private val ibDelete   : ImageButton = view.findViewById(R.id.ib_delete)
        private val ibAddTag   : ImageButton = view.findViewById(R.id.ib_add_tag)

        init {
            ibDelete.setOnClickListener{
                AlertDialog.Builder(context).apply {
                    setMessage(context.getText(R.string.msg_bookmark_del))
                    setPositiveButton(context.getString(android.R.string.ok)) { dialog: DialogInterface?, _:Int ->
                        if(items.size <= adapterPosition) {
                            dialog?.dismiss()
                            return@setPositiveButton
                        }
                        if(presenter.deleteBookmark(items[adapterPosition])) {
                            Toast.makeText(context, context.getText(R.string.msg_bookmark_del_success), Toast.LENGTH_SHORT).show()
                            addItems(presenter.getData())
                        }
                        else {
                            Toast.makeText(context, context.getText(R.string.msg_bookmark_del_fail), Toast.LENGTH_SHORT).show()
                        }
                    }
                    setNegativeButton(context.getString(android.R.string.cancel)) { dialog: DialogInterface?, _:Int ->
                        dialog?.dismiss()
                    }
                }.create().show()
            }
            ibAddTag.setOnClickListener{
                if(context is AppCompatActivity) {
                    TagDialogFragment(tvTags.text.toString(), adapterPosition, this@BookmarkAdapter)
                        .show(context.supportFragmentManager, TAG)
                }
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
                        tvSub.text    = "${it.secValue.substring(0,2)}-${it.secValue.substring(2)}"
                    }
                    true -> {
                        val route: RouteType = ConvertUtil.fromRouteType(it.secValue)
                        vFront.setBackgroundColor(context.getColor(route.colorId))
                        vBack.setBackgroundColor(context.getColor(route.colorId))
                        ivIcon.setImageResource(R.drawable.ic_directions_bus_2x)
                        tvSub.text    = "[${context.getText(route.nameId)}]"
                    }
                }
                tvTitle.text = it.name
                tvTags.visibility = if(it.tag.isNotEmpty()) View.VISIBLE else View.GONE
                tvTags.text = it.tag
            }
        }

        holder.itemView.setOnLongClickListener {
            dragListener.onStartDrag(holder)
            false
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

    override fun addTags(position: Int, tags: String) {
        items[position].tag = tags.replace("[", "").replace("]", "")
        notifyItemChanged(position)
        presenter.updateTag(items[position])
    }
}