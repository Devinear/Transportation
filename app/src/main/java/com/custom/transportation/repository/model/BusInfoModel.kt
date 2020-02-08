package com.custom.transportation.repository.model

import com.custom.transportation.common.BusType
import com.custom.transportation.common.Common
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
            = RetrofitHelper.getRetrofit(Common.baseUrl)
            .getStationByUid(Common.ServiceKey, arsId)
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
                    getBusType(item.busType1), item.rerideNum1, item.arrmsg2, getRouteType(item.routeType)))
            }
            presenter.searchSuccess()
        }
    }

    // 노선유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    private fun getRouteType(route: String) : RouteType {
        return when {
            route.compareTo("1") == 0 -> RouteType.AIRPORT
            route.compareTo("2") == 0 -> RouteType.TOWN
            route.compareTo("3") == 0 -> RouteType.BLUE
            route.compareTo("4") == 0 -> RouteType.GREEN
            route.compareTo("5") == 0 -> RouteType.YELLOW
            route.compareTo("6") == 0 -> RouteType.RED
            route.compareTo("7") == 0 -> RouteType.INCHEON
            route.compareTo("8") == 0 -> RouteType.GTEONGGI
            route.compareTo("9") == 0 -> RouteType.REMOVE
            else // if(route.compareTo("공용", false) == 0) {
            -> RouteType.COMMON
        }
    }

    // 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
    private fun getBusType(type: String) : BusType {
        return when {
            type.compareTo("2") == 0 -> BusType.BENDY
            type.compareTo("1") == 0 -> BusType.LOW
            else -> BusType.Common
        }
    }

    companion object {
        private var instance : BusInfoModel? = null
        fun getInstance(newPresenter: BusInfoPresenter) : BusInfoModel {
            instance ?: return BusInfoModel(newPresenter)
            instance!!.presenter = newPresenter
            return instance!!
        }
    }
}