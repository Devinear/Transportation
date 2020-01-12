package com.custom.transportation.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.ui.common.BUS_TAB
import com.custom.transportation.ui.fragment.*

class BusPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            BUS_TAB.NUMBER.pos -> { return BusNumberFragment.getInstance() }
//            BUS_TAB.STOP.pos   -> { return BusStopFragment.getInstance() }
            else -> { return BusStopFragment.getInstance() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            BUS_TAB.NUMBER.pos -> { return BusNumberFragment.getInstance().getTitle(context) }
//            BUS_TAB.STOP.pos -> { return BusStopFragment.getInstance().getTitle(context) }
            else -> { return BusStopFragment.getInstance().getTitle(context) }
        }
    }

    override fun getCount(): Int = 2
}