package com.custom.transportation.ui.fragment

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.custom.transportation.R

class HomeFragment : TabFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun getTitle(context: Context) : String = context.getString(R.string.title_home)

    override fun getDrawable(context: Context): Drawable? {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(android.R.drawable.ic_dialog_dialer)
        else
            return context.resources.getDrawable(android.R.drawable.ic_dialog_dialer)
    }

    override val fabClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {

        }
    }

    companion object {
//      @Volatile private var instance : HomeFragment? = null
        private var instance : HomeFragment? = null

        // Java : Static Method 처럼 사용할 수 있도록 한다.
        // also : apply와 같이 객체를 반환하며, let과 다르게 내부 결과(it)을 변화시킬 수 없다.
        @JvmStatic fun getInstance() : HomeFragment = instance ?: synchronized(this) {
                instance ?: HomeFragment().also { instance = it }
            }
    }
}