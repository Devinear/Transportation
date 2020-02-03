package com.custom.transportation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.ui.adapter.paper.SectionsPagerAdapter
import com.custom.transportation.ui.common.MainTab
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    val pagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
    var curTabType = MainTab.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = pagerAdapter

        findViewById<TabLayout>(R.id.tabs)?.run {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab ?: return

                    curTabType = when(tab.position) {
                        MainTab.BUS.pos -> MainTab.BUS
                        MainTab.SUBWAY.pos -> MainTab.SUBWAY
                        else -> MainTab.HOME
                    }
                    findViewById<FloatingActionButton>(R.id.fab).let {
                        it.setImageDrawable(pagerAdapter.getItem(curTabType.pos).getDrawable(applicationContext))
                        it.setOnClickListener(pagerAdapter.getItem(curTabType.pos).fabClickListener)
                    }
                }
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            })
            setupWithViewPager(viewPager)
        }
    }
}