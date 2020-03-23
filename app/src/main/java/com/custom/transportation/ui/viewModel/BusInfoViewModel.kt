package com.custom.transportation.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.custom.transportation.repository.BusInfoData

class BusInfoViewModel : ViewModel() {
    val busInfo : MutableLiveData<BusInfoData> by lazy {
        MutableLiveData<BusInfoData>()
    }
}