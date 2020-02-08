package com.custom.transportation.repository.model

import com.custom.transportation.common.Common
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import com.custom.transportation.ui.contract.BusInfoPresenter
import com.custom.transportation.ui.contract.BusStopPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusStopData
    (val arsId: Int,  val stId: Int, val stNm: String, val tmX: Float, val tmY: Float)

class BusStopModel(private var presenter: BusStopPresenter) : Callback<ServiceResult> {

    private val stopList : ArrayList<BusStopData> = ArrayList()

    fun searchWord(search: String)
            = RetrofitHelper.getRetrofit(Common.baseUrl)
            .getStationByName(Common.ServiceKey, search)
            .enqueue(this@BusStopModel)

    fun getBusStopData(): List<BusStopData> = stopList

    override fun onFailure(call: Call<ServiceResult>, t: Throwable)
            = presenter.searchFailure(t.message?:"NONE")

    override fun onResponse(call: Call<ServiceResult>, response: Response<ServiceResult>) {
        if(!response.isSuccessful) {
            presenter.searchFailure("NONE")
            return
        }
        response.body()?.msgHeader?.run {
            if(headerCd.toInt() != 0) {
                presenter.searchFailure(headerMsg)
                return@run
            }
        }
        response.body()?.msgBody?.run {
            stopList.clear()
            for(item in itemList) {
                stopList.add(BusStopData(item.arsId.toInt(),item.stId.toInt(),item.stNm,item.tmX.toFloat(),item.tmY.toFloat()))
            }
            presenter.searchSuccess()
        }
    }

    companion object {
        private var instance : BusStopModel? = null
        fun getInstance(newPresenter: BusStopPresenter) : BusStopModel {
            instance ?: return BusStopModel(newPresenter)
            instance!!.presenter = newPresenter
            return instance!!
        }
    }
}