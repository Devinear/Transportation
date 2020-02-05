package com.custom.transportation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.ui.adapter.paper.SectionsPagerAdapter
import com.custom.transportation.ui.common.MainTab
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = SectionsPagerAdapter(this, supportFragmentManager)

        findViewById<TabLayout>(R.id.tabs)?.run {
            setupWithViewPager(viewPager)
        }
    }
}