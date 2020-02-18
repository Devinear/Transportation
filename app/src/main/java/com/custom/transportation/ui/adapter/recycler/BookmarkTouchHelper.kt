package com.custom.transportation.ui.adapter.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(holder: BookmarkAdapter.ViewHolder)
}

interface OnItemMoveListener {
    fun onItemMove(fromPosition: Int, toPosition: Int)
}

class BookmarkTouchHelper(val moveListener: OnItemMoveListener) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder)
            : Int = makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.START or ItemTouchHelper.END)

    override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder)
            : Boolean {
        moveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}