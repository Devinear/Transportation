package com.custom.transportation.ui.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.R
import com.custom.transportation.ui.adapter.paper.BusPagerAdapter
import com.custom.transportation.ui.common.BusTab
import com.google.android.material.tabs.TabLayout

class BusFragment : TabFragment() {

    var fm : FragmentManager? = null
    lateinit var busAdapter : BusPagerAdapter
    var curTabType = BusTab.STOP

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View =  inflater.inflate(R.layout.fragment_bus, container, false)

        fm ?: return view
        context ?: return view

        val viewPager: ViewPager = view.findViewById(R.id.bus_pager)
        busAdapter = BusPagerAdapter(context!!, fm!!)
        viewPager.adapter = busAdapter

        view.findViewById<TabLayout>(R.id.bus_tabs)?.run {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab ?: return
                    curTabType = BusTab.values()[tab.position]
                    fabClickListener = (busAdapter.getItem(curTabType.pos) as TabFragment).fabClickListener
                }
                override fun onTabReselected(tab: TabLayout.Tab?) { }
                override fun onTabUnselected(tab: TabLayout.Tab?) { }
            })
            setupWithViewPager(viewPager)
        }

        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.title_bus)

    override fun getDrawable(context: Context): Drawable? = context.getDrawable(android.R.drawable.ic_search_category_default)

    override var fabClickListener = View.OnClickListener { }

    companion object {
        private var instance : BusFragment? = null
        fun getInstance() : BusFragment = instance ?: synchronized(this) {
            instance ?: BusFragment().also { instance = it }
        }
    }
}