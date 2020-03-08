package com.custom.transportation.ui.adapter.recycler

interface OnDragListener {
    fun onStartDrag(holder: BookmarkAdapter.ViewHolder)
}

interface OnItemMoveListener {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemIdle(fromPosition: Int, toPosition: Int)
}
