package com.custom.transportation.ui.common

enum class IntentType(val tpye: String) {
    ArsID("ArsID")
}

enum class MainTab(val pos: Int) {
    HOME(0), BUS(1), SUBWAY(2)
}

enum class BusTab(val pos: Int) {
    STOP(0), NUMBER(1)
}

class Common {
    companion object {
        /* https://www.data.go.kr */
        /* 일반 인증키(UTF-8) */
        const val ServiceKey = "SghQzAenAE240oXwJvXRN7X9jv8p1v7AD3tFDqwB0CBKxOWXGLZsbkFoIpWt0PueXq1GQTis5nhVsBDZiRSagw%3D%3D"
        const val baseUrl = "http://ws.bus.go.kr/api/rest/stationinfo/"
    }
}