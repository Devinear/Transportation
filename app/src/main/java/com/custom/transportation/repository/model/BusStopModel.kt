package com.custom.transportation.repository.model

import com.custom.transportation.common.CommonData
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import com.custom.transportation.ui.contract.BusStopPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusStopData
    (val arsId: String,  val stId: String, val stNm: String, val tmX: Float, val tmY: Float)

class BusStopModel(private var presenter: BusStopPresenter) : Callback<ServiceResult> {

    private val stopList : ArrayList<BusStopData> = ArrayList()

    fun searchWord(search: String) {

        for(ch: Char in search.toCharArray()) {
            if((ch < '0' || ch > '9') && ch != '-') {
                searchName(search)
                return
            }
        }

        // 실제 검색시 '19-406'으로는 검색을 하지 못한다.
        searchArsId(search.replace("-", ""))
    }

    private fun searchName(search: String) = RetrofitHelper.getRetrofit(CommonData.baseUrl)
        .getStationByName(CommonData.ServiceKey, search)
        .enqueue(this@BusStopModel)

    private fun searchArsId(search: String) = RetrofitHelper.getRetrofit(CommonData.baseUrl)
        .getStationByUid(CommonData.ServiceKey, search)
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
                if(item.tmX != "")
                    stopList.add(BusStopData(item.arsId,item.stId,item.stNm,item.tmX.toFloat(),item.tmY.toFloat()))
                else
                    stopList.add(BusStopData(item.arsId,item.stId,item.stNm,item.gpsX.toFloat(),item.gpsY.toFloat()))
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