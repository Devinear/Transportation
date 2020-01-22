package com.custom.transportation.ui.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.custom.transportation.R

class HomeFragment : TabFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_home, container, false)

    override fun getTitle(context: Context) : String = context.getString(R.string.title_star)

    override fun getDrawable(context: Context): Drawable? = context.getDrawable(android.R.drawable.ic_dialog_dialer)

    override val fabClickListener = View.OnClickListener { }

    companion object {
        private var instance : HomeFragment? = null
        fun getInstance() : HomeFragment = instance ?: synchronized(this) {
            instance ?: HomeFragment().also { instance = it }
        }
    }
}