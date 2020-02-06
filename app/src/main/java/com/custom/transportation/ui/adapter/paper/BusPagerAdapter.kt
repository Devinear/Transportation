package com.custom.transportation.ui.adapter.paper

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.common.BusTab
import com.custom.transportation.ui.fragment.BusNumberFragment
import com.custom.transportation.ui.fragment.BusStopFragment

class BusPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if(position == BusTab.NUMBER.pos)
            BusNumberFragment.getInstance()
        else
            BusStopFragment.getInstance()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if(position == BusTab.NUMBER.pos)
            BusNumberFragment.getInstance().getTitle(context)
        else
            BusStopFragment.getInstance().getTitle(context)
    }

    override fun getCount(): Int = 2
}