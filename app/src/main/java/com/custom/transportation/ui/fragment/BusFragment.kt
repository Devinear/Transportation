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
import com.google.android.material.tabs.TabLayout

class BusFragment : TabFragment() {

    var fm : FragmentManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View =  inflater.inflate(R.layout.fragment_bus, container, false)

        if(fm != null && context != null) {
            val viewPager: ViewPager = view.findViewById(R.id.bus_pager)
            viewPager.adapter = BusPagerAdapter(context!!, fm!!)
            val tabs: TabLayout = view.findViewById(R.id.bus_tabs)
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

    override val fabClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Bus Stop")
            val edit = EditText(context)
            edit.filters = Array<InputFilter>(1) {object : InputFilter {
                override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
                    if(source != null && source.matches("^[0-9-]".toRegex()))
                        return source
                    else
                        return ""
                }
            }}
            builder.setView(edit)
            builder.setPositiveButton(context!!.getString(android.R.string.ok), object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })
            builder.setNegativeButton(context!!.getString(android.R.string.cancel), object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            })
            builder.create().apply { show() }
        }
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