package com.custom.transportation.repository

import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.common.CommonData
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusStopData
    (val arsId: String,  val stId: String, val stNm: String, val tmX: Float, val tmY: Float)

class BusStopDataSourceImpl : BusStopDataSource, Callback<ServiceResult> {

    private var callback : BaseContract.RemoteCallback? = null
    private val stopList : ArrayList<BusStopData> = ArrayList()

    override fun search(search: String, callback: BaseContract.RemoteCallback) {
        this.callback = callback

        for(ch: Char in search.toCharArray()) {
            if((ch in '0'..'9') && ch != '-') {
                searchName(search)
                return
            }
        }

        // 실제 검색시 '19-406'으로는 검색을 하지 못한다.
        searchArsId(search.replace("-", ""))
    }

    private fun searchName(search: String) = RetrofitHelper.getRetrofit(CommonData.baseUrl)
        .getStationByName(CommonData.ServiceKey, search)
        .enqueue(this)

    private fun searchArsId(search: String) = RetrofitHelper.getRetrofit(CommonData.baseUrl)
        .getStationByUid(CommonData.ServiceKey, search)
        .enqueue(this)

    override fun getAll(): List<BusStopData> = stopList

    override fun onFailure(call: Call<ServiceResult>, t: Throwable) {
        callback?.onFailure(t.message?:"NONE")
    }

    override fun onResponse(call: Call<ServiceResult>, response: Response<ServiceResult>) {
        if(!response.isSuccessful) {
            callback?.onFailure("NONE")
            return
        }
        response.body()?.msgHeader?.run {
            if(headerCd.toInt() != 0) {
                callback?.onFailure(headerMsg)
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
            callback?.onSuccess()
        }
    }

    companion object {
        private var INSTANCE : BusStopDataSource? = null
        fun getInstance() : BusStopDataSource =
            INSTANCE ?: BusStopDataSourceImpl().also { INSTANCE = it }
    }
}