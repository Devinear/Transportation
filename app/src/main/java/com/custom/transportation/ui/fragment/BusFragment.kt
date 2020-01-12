package com.custom.transportation.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.custom.transportation.R
import com.custom.transportation.ui.adapter.BusPagerAdapter
import com.custom.transportation.ui.common.BUS_TAB
import com.custom.transportation.ui.common.MAIN_TAB
import com.google.android.material.tabs.TabLayout

class BusFragment : TabFragment() {

    var fm : FragmentManager? = null
    lateinit var busAdapter : BusPagerAdapter
    var curTabType = BUS_TAB.STOP

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View =  inflater.inflate(R.layout.fragment_bus, container, false)

        if(fm != null && context != null) {
            val viewPager: ViewPager = view.findViewById(R.id.bus_pager)
            busAdapter = BusPagerAdapter(context!!, fm!!)
            viewPager.adapter = busAdapter
            val tabs: TabLayout = view.findViewById(R.id.bus_tabs)

            tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) { }

                override fun onTabUnselected(p0: TabLayout.Tab?) { }

                override fun onTabSelected(p0: TabLayout.Tab?) {
                    if(p0 == null) return

                    when(p0.position) {
                        BUS_TAB.NUMBER.pos -> {curTabType = BUS_TAB.NUMBER }
//                        BUS_TAB.STOP.pos -> { curTabType = BUS_TAB.STOP }
                        else -> { curTabType = BUS_TAB.STOP }
                    }

                    fabClickListener = (busAdapter.getItem(curTabType.pos) as TabFragment).fabClickListener
                }
            })
            tabs.setupWithViewPager(viewPager)
        }
        return view
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.title_bus)

    override fun getDrawable(context: Context): Drawable? {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(android.R.drawable.ic_search_category_default)
        else
            return context.resources.getDrawable(android.R.drawable.ic_search_category_default)
    }

    override var fabClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) { }
    }

    companion object {
        // volatile : 변수를 Main Memory에 저장하겠다라는 것을 명시한다.
        // 변수의 값을 Read할 때마다 CPU cache에 저장된 값이 아닌 Main Memory에서 읽고, Write할 때마다 Main Memory에 까지 작성한다.
        // 즉, Multi Thread 환경을 대비하기 위함이다.
//      @Volatile private var instance : BusFragment? = null
        private var instance : BusFragment? = null

        // Java : Static Method 처럼 사용할 수 있도록 한다.
        // also : apply와 같이 객체를 반환하며, let과 다르게 내부 결과(it)을 변화시킬 수 없다.
        @JvmStatic fun getInstance() : BusFragment = instance ?: synchronized(this) {
            instance ?: BusFragment().also { instance = it }
        }
    }
}