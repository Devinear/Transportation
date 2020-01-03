package com.custom.transportation.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.ui.fragment.BusFragment
import com.custom.transportation.ui.fragment.HomeFragment
import com.custom.transportation.ui.fragment.SubwayFragment

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            1 -> { return BusFragment.getInstance() }
            2 -> { return SubwayFragment.getInstance() }
            else -> { return HomeFragment.getInstance() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            1 -> { return BusFragment.getInstance().getTitle(context) }
            2 -> { return SubwayFragment.getInstance().getTitle(context) }
            else -> { return HomeFragment.getInstance().getTitle(context) }
        }
    }

    override fun getCount(): Int = 3
}