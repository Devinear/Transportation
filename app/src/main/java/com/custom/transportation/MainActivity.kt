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
    val pagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
    var curTabType = MainTab.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = pagerAdapter

        fab = findViewById(R.id.fab)
        findViewById<TabLayout>(R.id.tabs)?.run {
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab ?: return
                    curTabType = MainTab.values()[tab.position]

                    fab.setImageDrawable(pagerAdapter.getItem(curTabType.pos).getDrawable(applicationContext))
                    fab.setOnClickListener(pagerAdapter.getItem(curTabType.pos).fabClickListener)
                }
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            })
            setupWithViewPager(viewPager)
        }
    }
}