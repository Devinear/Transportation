package com.custom.transportation.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.R
import com.custom.transportation.ui.adapter.paper.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private val pagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = pagerAdapter

        findViewById<TabLayout>(R.id.tabs)?.run {
            addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab ?: return
                    pagerAdapter.getItem(tab.position).showFragment()
                }
                override fun onTabReselected(p0: TabLayout.Tab?) = Unit
                override fun onTabUnselected(p0: TabLayout.Tab?) = Unit
            })
            setupWithViewPager(viewPager)
        }
    }
}