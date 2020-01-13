package com.custom.transportation

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.ui.adapter.SectionsPagerAdapter
import com.custom.transportation.ui.common.MAIN_TAB
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var pagerAdapter : SectionsPagerAdapter
    var curTabType = MAIN_TAB.HOME
    val REQUEST_CODE = 1


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

        checkPermission()
    }

    private fun checkPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray ) {
        when(requestCode) {
            REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    return
                else
                    Toast.makeText(this, "PERMISSION_DENIED!", Toast.LENGTH_SHORT).show()
            }
        }
        finish()
    }
}