package com.custom.transportation.common

object ConvertUtil {

    // 노선유형 (1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용)
    fun fromRouteType(route: String) : RouteType {
        return when (route) {
            "1" -> RouteType.AIRPORT
            "2" -> RouteType.TOWN
            "3" -> RouteType.BLUE
            "4" -> RouteType.GREEN
            "5" -> RouteType.YELLOW
            "6" -> RouteType.RED
            "7" -> RouteType.INCHEON
            "8" -> RouteType.GTEONGGI
            "9" -> RouteType.REMOVE
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
        return when (type) {
            "2"  -> BusType.BENDY
            "1"  -> BusType.LOW
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