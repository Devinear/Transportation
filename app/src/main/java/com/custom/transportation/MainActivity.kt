package com.custom.transportation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.ui.adapter.SectionsPagerAdapter
import com.custom.transportation.ui.common.MAIN_TAB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var pagerAdapter : SectionsPagerAdapter
    var curTabType = MAIN_TAB.HOME


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        pagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = pagerAdapter

        fab = findViewById(R.id.fab)

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) { }

            override fun onTabUnselected(p0: TabLayout.Tab?) { }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if(p0 == null) return

                when(p0.position) {
                    MAIN_TAB.BUS.pos -> { curTabType = MAIN_TAB.BUS }
                    MAIN_TAB.SUBWAY.pos -> { curTabType = MAIN_TAB.SUBWAY }
                    else -> { curTabType = MAIN_TAB.HOME }
                }

                val fragment = pagerAdapter.getItem(curTabType.pos)
                fab.setImageDrawable(fragment.getDrawable(applicationContext))
                fab.setOnClickListener(fragment.fabClickListener)
            }
        })
        tabs.setupWithViewPager(viewPager)
    }
}