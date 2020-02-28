package com.custom.transportation.common

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