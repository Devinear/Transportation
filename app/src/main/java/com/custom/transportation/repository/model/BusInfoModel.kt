package com.custom.transportation.repository.model

import com.custom.transportation.common.Common
import com.custom.transportation.repository.remote.RetrofitHelper
import com.custom.transportation.repository.remote.ServiceResult
import com.custom.transportation.ui.contract.BusInfoPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BusInfoData
    (val name: String, val time: String, val direction: String, val before: String, val after: String)

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
                infoList.add(BusInfoData(item.rtNm, item.arrmsg1, item.arrmsg2, item.adirection, item.busType1))
            }
            presenter.searchSuccess()
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