package com.custom.transportation.model

import com.custom.transportation.data.retrofit.RetrofitHelper
import com.custom.transportation.data.retrofit.ServiceResult
import com.custom.transportation.data.unit.BookmarkDatabase
import com.custom.transportation.data.unit.BusInfoData
import com.custom.transportation.data.unit.BusInfoDatabase
import com.custom.transportation.presenter.BusStopDetailPresenter
import com.custom.transportation.ui.common.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusStopDetailModel(private val presenter: BusStopDetailPresenter) : Callback<ServiceResult> {

    fun searchArsId(arsId: Int)
            = RetrofitHelper.getRetrofit(Common.baseUrl)
            .getStationByUid(Common.ServiceKey, arsId.toString())
            .enqueue(this@BusStopDetailModel)

    fun addBookmark(data: BusInfoData) { BookmarkDatabase.add(data) }

    fun getBusStopData(): List<BusInfoData> = BusInfoDatabase.getAll()

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
            BusInfoDatabase.clear()
            for(item in itemList) {
                BusInfoDatabase.add(item.rtNm, item.arrmsg1, item.arrmsg2, item.adirection, item.busType1)
            }
            presenter.searchSuccess(BusInfoDatabase.getAll())
        }
    }

}