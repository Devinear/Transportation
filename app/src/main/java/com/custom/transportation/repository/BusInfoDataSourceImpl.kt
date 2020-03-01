package com.custom.transportation.repository

import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.common.BusType
import com.custom.transportation.common.CommonData
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.common.RouteType
import com.custom.transportation.repository.remote.ResponseResult
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusInfoData
    (val name: String, val time: String, val direction: String,
     val thisType: BusType, val thisCount: String, val after: String, val routeType: RouteType)

class BusInfoDataSourceImpl : BaseDataSource<BusInfoData>() {
    private val items : ArrayList<BusInfoData> = ArrayList()

    override fun search(search: String, callback: BaseContract.RemoteCallback) {

        CoroutineScope(Dispatchers.IO).launch {
            val response = search { RetrofitHelper.getRetrofit(CommonData.baseUrl)
                .getStationByUid(CommonData.ServiceKey, search) }
            when(response) {
                is ResponseResult.Success -> {
                    items.clear()
                    response.items.forEach { item ->
                        items.add(
                            BusInfoData(
                                item.rtNm, item.arrmsg1, item.adirection,
                                ConvertUtil.fromBusType(item.busType1), item.rerideNum1, item.arrmsg2, ConvertUtil.fromRouteType(item.routeType)
                            )
                        )
                    }
                    callback.onSuccess()
                }
                is ResponseResult.Failure -> {
                    callback.run { onFailure(msg = response.msg) }
                }
            }
        }

    }

    override fun getAll(): List<BusInfoData> = items

    companion object {
        val INSTANCE by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BusInfoDataSourceImpl()
        }
    }
}