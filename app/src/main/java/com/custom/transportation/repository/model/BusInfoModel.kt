package com.custom.transportation.repository.model

import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import com.custom.transportation.repository.unit.BookmarkDatabase
import com.custom.transportation.repository.unit.BusInfoData
import com.custom.transportation.repository.unit.BusInfoDatabase
import com.custom.transportation.ui.contract.BusInfoPresenter
import com.custom.transportation.common.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusInfoModel(private val presenter: BusInfoPresenter) : Callback<ServiceResult> {

    fun searchArsId(arsId: String)
            = RetrofitHelper.getRetrofit(Common.baseUrl)
            .getStationByUid(Common.ServiceKey, arsId)
            .enqueue(this@BusInfoModel)

    fun addBookmark(data: BusInfoData) = BookmarkDatabase.add(data)

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