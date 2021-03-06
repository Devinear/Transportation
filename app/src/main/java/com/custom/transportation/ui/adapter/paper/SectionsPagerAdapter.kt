package com.custom.transportation.ui.adapter.paper

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.custom.transportation.ui.base.BaseFragment
import com.custom.transportation.common.MainTab
import com.custom.transportation.ui.view.fragment.BookmarkFragment
import com.custom.transportation.ui.view.fragment.BusStopFragment

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): BaseFragment {
        return when(position) {
            MainTab.BUS.pos -> BusStopFragment.INSTANCE
            else -> BookmarkFragment.INSTANCE
        }
    }

    override fun getPageTitle(position: Int): CharSequence? = getItem(position).getTitle(context)

    override fun getCount(): Int = 2
}