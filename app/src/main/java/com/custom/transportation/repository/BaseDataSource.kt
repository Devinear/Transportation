package com.custom.transportation.repository

import com.custom.transportation.repository.remote.ResponseResult
import com.custom.transportation.repository.remote.ServiceResult
import com.custom.transportation.ui.base.BaseContract
import retrofit2.Response

abstract class BaseDataSource <T> {
    suspend fun search(search: suspend () -> Response<ServiceResult>) : ResponseResult {
        val response : Response<ServiceResult>
        try {
            response = search.invoke()
        }
        catch (e : Exception) {
            return ResponseResult.Failure(e.message.toString())
        }

        return if(response.isSuccessful) {
            if(response.body()?.msgHeader?.headerCd?.toInt() != 0)
                ResponseResult.Failure(response.body()?.msgHeader?.headerMsg?:"HEADER CODE ERROR")
            else
                ResponseResult.Success(response.body()?.msgBody?.itemList?:mutableListOf())
        } else {
            ResponseResult.Failure("Not Success")
        }
    }

    abstract fun search(search: String, callback: BaseContract.RemoteCallback)
    abstract fun getAll() : List<T>
}