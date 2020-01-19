package com.custom.transportation.ui.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.ui.common.MainTab
import com.custom.transportation.ui.fragment.BusFragment
import com.custom.transportation.ui.fragment.HomeFragment
import com.custom.transportation.ui.fragment.SubwayFragment
import com.custom.transportation.ui.fragment.TabFragment

class SectionsPagerAdapter(private val context: Context, private val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): TabFragment {
        when(position) {
            // 1
            MainTab.BUS.pos -> {
                BusFragment.getInstance().fm = fm
                return BusFragment.getInstance()
            }

            // 2
            MainTab.SUBWAY.pos -> { return SubwayFragment.getInstance() }

            // 0
            else -> { return HomeFragment.getInstance() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            MainTab.BUS.pos -> { return BusFragment.getInstance().getTitle(context) }
            MainTab.SUBWAY.pos -> { return SubwayFragment.getInstance().getTitle(context) }
            else -> { return HomeFragment.getInstance().getTitle(context) }
        }
    }

    override fun getCount(): Int = 3
}