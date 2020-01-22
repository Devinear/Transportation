package com.custom.transportation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.ui.adapter.paper.SectionsPagerAdapter
import com.custom.transportation.ui.common.MainTab
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var pagerAdapter : SectionsPagerAdapter
    var curTabType = MainTab.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        pagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = pagerAdapter

        fab = findViewById(R.id.fab)

        findViewById<TabLayout>(R.id.tabs)?.run {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab ?: return

                    curTabType = when(tab.position) {
                        MainTab.BUS.pos -> MainTab.BUS
                        MainTab.SUBWAY.pos -> MainTab.SUBWAY
                        else -> MainTab.HOME
                    }
                    val fragment = pagerAdapter.getItem(curTabType.pos)
                    fab.setImageDrawable(fragment.getDrawable(applicationContext))
                    fab.setOnClickListener(fragment.fabClickListener)
                }
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            })
            setupWithViewPager(viewPager)
        }
    }
}