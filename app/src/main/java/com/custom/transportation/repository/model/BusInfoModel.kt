package com.custom.transportation.repository.model

import com.custom.transportation.common.BusType
import com.custom.transportation.common.CommonData
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.common.RouteType
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import com.custom.transportation.ui.contract.BusInfoPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusInfoData
    (val name: String, val time: String, val direction: String,
     val thisType: BusType, val thisCount: String,  val after: String, val routeType: RouteType)

class BusInfoModel(private var presenter: BusInfoPresenter) : Callback<ServiceResult> {

    private val infoList : ArrayList<BusInfoData> = ArrayList()

    fun searchArsId(arsId: String)
            = RetrofitHelper.getRetrofit(CommonData.baseUrl)
            .getStationByUid(CommonData.ServiceKey, arsId)
            .enqueue(this@BusInfoModel)

    fun getBusStopData(): List<BusInfoData> = infoList

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
            infoList.clear()
            for(item in itemList) {
                infoList.add(BusInfoData(item.rtNm, item.arrmsg1, item.adirection,
                    ConvertUtil.fromBusType(item.busType1), item.rerideNum1, item.arrmsg2, ConvertUtil.fromRouteType(item.routeType)))
            }
            presenter.searchSuccess()
        }
    }

    companion object {
        private var instance : BusInfoModel? = null
        fun getInstance(newPresenter: BusInfoPresenter) : BusInfoModel {
            instance ?: return BusInfoModel(newPresenter).also {
                it.presenter = newPresenter
                instance = it
            }
            return instance!!
        }
    }
}