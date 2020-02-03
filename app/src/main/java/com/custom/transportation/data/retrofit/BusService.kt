package com.custom.transportation.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusService {

    /* 검색어가 포함된 정류소 명칭을 조회한다. */
    /* serviceKey - 인증키 */
    /* stSrch     - 정류소명 검색어(한글 URL Encoding) */
    /* http://ws.bus.go.kr/api/rest/stationinfo/getStationByName */
    @GET("getStationByName")
    fun getStationByName(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("stSrch", encoded = false) stCrch: String
    ) : Call<ServiceResult>

    /* 노선 고유번호에 해당하는 정류소 정보를 조회한다. */
    /* serviceKey - 인증키 */
    /* arsId      - 정류소 고유번호 */
    /* http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid */
    @GET("getStationByUid")
    fun getStationByUid(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("arsId", encoded = true) arsId: String
    ) : Call<ServiceResult>

    /* 지정된 좌표와 반경 범위 내의 정류소 목록을 조회한다. */
    /* serviceKey - 인증키 */
    /* tmX        - 기준위치 x */
    /* tmY        - 기준위치 y */
    /* radius     - 검색반경 */
    /* http://ws.bus.go.kr/api/rest/stationinfo/getStationByPos */
    @GET("getStationByPos")
    fun getStationByPos(
        @Query("serviceKey", encoded = true) serviceKey: String,
        @Query("tmX", encoded = true) tmX: String,
        @Query("tmY", encoded = true) tmY: String,
        @Query("radius", encoded = true) radius: String
    ) : Call<ServiceResult>
}