package com.custom.transportation.repository

import android.util.Log
import com.custom.transportation.ui.base.BaseContract
import com.custom.transportation.common.CommonData
import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.repository.remote.ResponseResult
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusStopData
    (val arsId: String,  val stId: String, val stNm: String, val tmX: Float, val tmY: Float)

class BusStopDataSourceImpl : BaseDataSource<BusStopData>() {

    private val stopList : ArrayList<BusStopData> = ArrayList()

    override fun search(search: String, callback: BaseContract.RemoteCallback) {
        Log.d("BusStopDataSourceImpl", "search 1111")

        CoroutineScope(Dispatchers.IO).launch {

            Log.d("BusStopDataSourceImpl", "search launch")

            var response : ResponseResult? = null
            for(ch: Char in search.toCharArray()) {
                if((ch !in '0'..'9') && ch != '-') {
                    response = searchName(search)
                    break
                }
            }
            // 실제 검색시 '19-406'으로는 검색을 하지 못한다.
            response ?: searchArsId(search.replace("-", ""))

            Log.d("BusStopDataSourceImpl", "search response")


            when(response) {
                is ResponseResult.Success -> {
                    stopList.clear()
                    response.items.forEach { item ->
                        if(item.tmX != "")
                            stopList.add(BusStopData(item.arsId,item.stId,item.stNm,item.tmX.toFloat(),item.tmY.toFloat()))
                        else
                            stopList.add(BusStopData(item.arsId,item.stId,item.stNm,item.gpsX.toFloat(),item.gpsY.toFloat()))
                    }
                    withContext(Dispatchers.Main) {
                        callback.onSuccess()
                    }
                }
                is ResponseResult.Failure -> {
                    withContext(Dispatchers.Main) {
                        callback.run { onFailure(msg = response.msg) }
                    }
                }
            }
        }

        Log.d("BusStopDataSourceImpl", "search 2222")

    }

    private suspend fun searchName(search: String) : ResponseResult = search { RetrofitHelper.getRetrofit(CommonData.baseUrl)
            .getStationByName(CommonData.ServiceKey, search) }

    private suspend fun searchArsId(search: String) : ResponseResult = search { RetrofitHelper.getRetrofit(CommonData.baseUrl)
        .getStationByUid(CommonData.ServiceKey, search) }

    override fun getAll(): List<BusStopData> = stopList

    companion object {
        val INSTANCE by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            BusStopDataSourceImpl()
        }
    }
}