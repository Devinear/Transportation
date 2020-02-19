package com.custom.transportation.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.R
import com.custom.transportation.ui.base.BaseFragment
import com.custom.transportation.ui.adapter.paper.BusPagerAdapter
import com.google.android.material.tabs.TabLayout

class BusFragment : BaseFragment() {

    private lateinit var busAdapter : BusPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View =  inflater.inflate(R.layout.fragment_bus, container, false)

        context ?: return view
        fragmentManager ?: return view
        busAdapter = BusPagerAdapter(context!!, fragmentManager!!)

        val viewPager: ViewPager = view.findViewById(R.id.bus_pager)
        viewPager.adapter = busAdapter
        view.findViewById<TabLayout>(R.id.bus_tabs)?.run {
            setupWithViewPager(viewPager)
        }
        return view
    }

    override fun showFragment() = Unit

    override fun getTitle(context: Context) : String = context.getString(R.string.title_bus)

    companion object {
        private var instance : BusFragment? = null
        fun getInstance() : BusFragment = instance ?: synchronized(this) {
            instance ?: BusFragment().also { instance = it }
        }
    }
}