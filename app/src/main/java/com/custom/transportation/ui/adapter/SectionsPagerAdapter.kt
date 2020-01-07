package com.custom.transportation.ui.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.ui.common.MAIN_TAB
import com.custom.transportation.ui.fragment.BusFragment
import com.custom.transportation.ui.fragment.HomeFragment
import com.custom.transportation.ui.fragment.SubwayFragment
import com.custom.transportation.ui.fragment.TabFragment

class SectionsPagerAdapter(private val context: Context, private val fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): TabFragment {
        when(position) {
            // 1
            MAIN_TAB.BUS.pos -> {
                BusFragment.getInstance().fm = fm
                return BusFragment.getInstance()
            }

            // 2
            MAIN_TAB.SUBWAY.pos -> { return SubwayFragment.getInstance() }

            // 0
            else -> { return HomeFragment.getInstance() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            MAIN_TAB.BUS.pos -> { return BusFragment.getInstance().getTitle(context) }
            MAIN_TAB.SUBWAY.pos -> { return SubwayFragment.getInstance().getTitle(context) }
            else -> { return HomeFragment.getInstance().getTitle(context) }
        }
    }

    override fun getCount(): Int = 3
}