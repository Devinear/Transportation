package com.custom.transportation.repository.remote

import com.android.volley.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object RetrofitHelper {

    fun getRetrofit(baseUrl: String) : BusService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(SimpleXmlConverterFactory.create()).also {
                if(BuildConfig.DEBUG)
                    it.client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build())
            }
            .build()
            .create(BusService::class.java)
}