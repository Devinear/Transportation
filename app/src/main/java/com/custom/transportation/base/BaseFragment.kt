package com.custom.transportation.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract fun showFragment()
    abstract fun getTitle(context: Context) : String
}