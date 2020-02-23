package com.custom.transportation.ui.adapter.recycler

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface OnDragListener {
    fun onStartDrag(holder: BookmarkAdapter.ViewHolder)
}

interface OnItemMoveListener {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemIdle(fromPosition: Int, toPosition: Int)
}

class BookmarkTouchHelper(private val moveListener: OnItemMoveListener) : ItemTouchHelper.Callback() {

    private var isActionStateDrag : Boolean = false
    private var fromPosition: Int = -1
    private var toPosition: Int = -1

    override fun getMovementFlags(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder)
            : Int = makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.START or ItemTouchHelper.END)

    override fun onMove(recyclerView: RecyclerView,viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder)
            : Boolean {
        Log.d("BookmarkTouchHelper", "onMove from:${fromPosition} to:${toPosition}")
        // from의 경우 move 동작 후 최초의 값만 필요하다.
        if(fromPosition == -1)
            fromPosition = viewHolder.adapterPosition
        toPosition = target.adapterPosition

        moveListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) = Unit

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        Log.d("BookmarkTouchHelper", "onSelectedChanged actionState:${actionState} from:${fromPosition} to:${toPosition}")
        if(actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            isActionStateDrag = true
        }
        else if(isActionStateDrag && actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
            isActionStateDrag = false
            moveListener.onItemIdle(fromPosition, toPosition)

            fromPosition = -1
            toPosition = -1
        }
        super.onSelectedChanged(viewHolder, actionState)
    }
}