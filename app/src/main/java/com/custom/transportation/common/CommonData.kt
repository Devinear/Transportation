package com.custom.transportation.common

import android.content.Context

object CommonData {
    /* https://www.data.go.kr */
    /* 일반 인증키(UTF-8) */
    const val ServiceKey = "SghQzAenAE240oXwJvXRN7X9jv8p1v7AD3tFDqwB0CBKxOWXGLZsbkFoIpWt0PueXq1GQTis5nhVsBDZiRSagw%3D%3D"
    const val baseUrl = "http://ws.bus.go.kr/api/rest/stationinfo/"

    // Database 생성을 위한 application Context
    var appContext : Context? = null
}