package com.custom.transportation.repository.remote

import retrofit2.Response

sealed class ResponseResult {
    data class Success(val items: List<Item>) : ResponseResult()
    data class Failure(val msg: String) : ResponseResult()
}