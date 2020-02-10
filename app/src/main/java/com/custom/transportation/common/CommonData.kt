package com.custom.transportation.common

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.custom.transportation.R

enum class IntentType(val type: String) {
    ArsID("ArsID")
}

enum class MainTab(val pos: Int) {
    BOOKMARK(0), BUS(1)
}

enum class BusTab(val pos: Int) {
    STOP(0), NUMBER(1)
}

// 차량유형 
// 0:일반버스, 1:저상버스, 2:굴절버스
enum class BusType(val typeId: Int) {
    COMMON(R.string.bus_common), LOW(R.string.bus_low_floor), BENDY(R.string.bus_bendy)
}

// 노선유형 
// 1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용
enum class RouteType(@ColorRes val colorId: Int, @StringRes val nameId: Int) {
    AIRPORT(R.color.bus_common, R.string.route_air), TOWN(R.color.bus_town, R.string.route_town),
    BLUE(R.color.bus_blue, R.string.route_blue), GREEN(R.color.bus_green, R.string.route_green),
    YELLOW(R.color.bus_yellow, R.string.route_yellow), RED(R.color.bus_red, R.string.route_red),
    INCHEON(R.color.bus_common, R.string.route_incheon), GTEONGGI(R.color.bus_common, R.string.route_gteonggi),
    REMOVE(R.color.bus_common, R.string.route_remove), COMMON(R.color.bus_common, R.string.route_common)
}

object CommonData {
    /* https://www.data.go.kr */
    /* 일반 인증키(UTF-8) */
    const val ServiceKey = "SghQzAenAE240oXwJvXRN7X9jv8p1v7AD3tFDqwB0CBKxOWXGLZsbkFoIpWt0PueXq1GQTis5nhVsBDZiRSagw%3D%3D"
    const val baseUrl = "http://ws.bus.go.kr/api/rest/stationinfo/"

    // Database 생성을 위한 application Context
    var appContext : Context? = null
}

object ConvertUtil {

    // 노선유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    fun fromRouteType(route: String) : RouteType {
        return when {
            route.compareTo("1") == 0 -> RouteType.AIRPORT
            route.compareTo("2") == 0 -> RouteType.TOWN
            route.compareTo("3") == 0 -> RouteType.BLUE
            route.compareTo("4") == 0 -> RouteType.GREEN
            route.compareTo("5") == 0 -> RouteType.YELLOW
            route.compareTo("6") == 0 -> RouteType.RED
            route.compareTo("7") == 0 -> RouteType.INCHEON
            route.compareTo("8") == 0 -> RouteType.GTEONGGI
            route.compareTo("9") == 0 -> RouteType.REMOVE
            else // if(route.compareTo("0", false) == 0) {
            -> RouteType.COMMON
        }
    }
    fun toRouteType(type: RouteType) : String {
        return when(type) {
            RouteType.COMMON  -> "0"
            RouteType.AIRPORT -> "1"
            RouteType.TOWN    -> "2"
            RouteType.BLUE    -> "3"
            RouteType.GREEN   -> "4"
            RouteType.YELLOW  -> "5"
            RouteType.RED     -> "6"
            RouteType.INCHEON -> "7"
            RouteType.GTEONGGI-> "8"
            RouteType.REMOVE  -> "9"
        }
    }

    // 차량유형 (0:일반버스, 1:저상버스, 2:굴절버스)
    fun fromBusType(type: String) : BusType {
        return when {
            type.compareTo("2") == 0 -> BusType.BENDY
            type.compareTo("1") == 0 -> BusType.LOW
            else -> BusType.COMMON
        }
    }
    fun toBusType(type: BusType) : String {
        return when(type) {
            BusType.COMMON -> "0"
            BusType.LOW    -> "1"
            BusType.BENDY  -> "2"
        }
    }
}