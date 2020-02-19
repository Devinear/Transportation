package com.custom.transportation.repository

import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.common.BusType
import com.custom.transportation.common.CommonData
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.common.RouteType
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusInfoData
    (val name: String, val time: String, val direction: String,
     val thisType: BusType, val thisCount: String, val after: String, val routeType: RouteType)

class BusInfoDataSourceImpl : BusInfoDataSource, Callback<ServiceResult> {
    private var callback: BaseContract.RemoteCallback? = null
    private val items : ArrayList<BusInfoData> = ArrayList()

    override fun search(arsId: String, callback: BaseContract.RemoteCallback) {
        this.callback = callback

        RetrofitHelper.getRetrofit(CommonData.baseUrl)
            .getStationByUid(CommonData.ServiceKey, arsId)
            .enqueue(this)
    }

    override fun getAll(): List<BusInfoData> = items

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
            items.clear()
            for(item in itemList) {
                items.add(BusInfoData(item.rtNm, item.arrmsg1, item.adirection,
                    ConvertUtil.fromBusType(item.busType1), item.rerideNum1, item.arrmsg2, ConvertUtil.fromRouteType(item.routeType)))
            }
            callback?.onSuccess()
        }
    }

    companion object {
        private var INSTANCE : BusInfoDataSource? = null
        fun getInstance() : BusInfoDataSource =
            INSTANCE ?: BusInfoDataSourceImpl().also { INSTANCE = it }
    }
}