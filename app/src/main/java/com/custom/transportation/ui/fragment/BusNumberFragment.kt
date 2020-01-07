package com.custom.transportation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.custom.transportation.R

class BusNumberFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_subway, container, false)
    }

    fun getTitle(context: Context) : String = context.getString(R.string.busNumber)

    companion object {
        private var instance : BusNumberFragment? = null
        @JvmStatic fun getInstance() : BusNumberFragment = instance ?: synchronized(this) {
            instance ?: BusNumberFragment().also { instance = it }
        }
    }
}