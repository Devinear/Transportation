package com.custom.transportation.ui.common

enum class MAIN_TAB(val pos: Int) {
    HOME(0), BUS(1), SUBWAY(2)
}

enum class BUS_TAB(val pos: Int) {
    STOP(0), NUMBER(1)
}

class Common {
    companion object {
        /* https://www.data.go.kr */
        /* 일반 인증키(UTF-8) */
        val ServiceKey = "SghQzAenAE240oXwJvXRN7X9jv8p1v7AD3tFDqwB0CBKxOWXGLZsbkFoIpWt0PueXq1GQTis5nhVsBDZiRSagw%3D%3D"
    }
}

object CallBackUrl {
    /* 검색어가 포함된 정류소 명칭을 조회한다. */
    /* serviceKey - 인증키 */
    /* stSrch     - 정류소명 검색어(한글 URL Encoding) */
    val getStationByNameList = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName"

    /* 지정된 좌표와 반경 범위 내의 정류소 목록을 조회한다. */
    /* serviceKey - 인증키 */
    /* tmX        - 기준위치 x */
    /* tmY        - 기준위치 y */
    /* radius     - 검색반경 */
    val getStaionsByPosList = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByPos"

    /* 노선 고유번호에 해당하는 정류소 정보를 조회한다. */
    /* serviceKey - 인증키 */
    /* arsId      - 정류소 고유번호 */
    val getStationByUidItem = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid"
}

interface ParserListener {
    fun onParserSuccess()
    fun onParserFail()
}