package com.custom.transportation.model

import com.custom.transportation.data.retrofit.RetrofitHelper
import com.custom.transportation.data.retrofit.ServiceResult
import com.custom.transportation.data.unit.BookmarkDatabase
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.presenter.BusStopPresenter
import com.custom.transportation.ui.common.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusStopModel(private val presenter: BusStopPresenter) : Callback<ServiceResult> {

    fun searchWord(search: String)
            = RetrofitHelper.getRetrofit(Common.baseUrl)
            .getStationByName(Common.ServiceKey, search)
            .enqueue(this@BusStopModel)

    fun addBookmark(data: BusStopData) { BookmarkDatabase.add(data) }

    fun getBusStopData(): List<BusStopData> = BusStopDatabase.getAll()

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
            BusStopDatabase.clear()
            for(item in itemList) {
                BusStopDatabase.add(item.arsId.toInt(),item.stId.toInt(),item.stNm,item.tmX.toFloat(),item.tmY.toFloat())
            }
            presenter.searchSuccess(BusStopDatabase.getAll())
        }
    }
}