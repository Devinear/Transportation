package com.custom.transportation.ui.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.fragment.app.Fragment

abstract class TabFragment : Fragment() {
    abstract val fabClickListener : View.OnClickListener
    abstract fun getTitle(context: Context) : String
    abstract fun getDrawable(context: Context) : Drawable?
}