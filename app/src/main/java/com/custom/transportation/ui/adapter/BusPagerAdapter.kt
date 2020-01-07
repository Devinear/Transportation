package com.custom.transportation.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.ui.fragment.*

class BusPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> { return BusStopFragment.getInstance() }
            else -> { return BusNumberFragment.getInstance() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> { return BusStopFragment.getInstance().getTitle(context) }
            else -> { return BusNumberFragment.getInstance().getTitle(context) }
        }
    }

    override fun getCount(): Int = 2
}