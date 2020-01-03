package com.custom.transportation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.custom.transportation.R

class SubwayFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subway, container, false)
    }

    fun getTitle(context: Context) : String = context.getString(R.string.title_subway)

    companion object {
//      @Volatile private var instance : SubwayFragment? = null
        private var instance : SubwayFragment? = null

        // Java : Static Method 처럼 사용할 수 있도록 한다.
        // also : apply와 같이 객체를 반환하며, let과 다르게 내부 결과(it)을 변화시킬 수 없다.
        @JvmStatic fun getInstance() : SubwayFragment = instance ?: synchronized(this) {
            instance ?: SubwayFragment().also { instance = it }
        }
    }
}