package com.custom.transportation.repository.mapper

import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.repository.BusStopData

object BusStopMapperImpl : Mapper<BusStopData, BookmarkData> {

    override fun toBookmark(data: BusStopData): BookmarkData =
        BookmarkData(
            key = -1, isBusInfo = false, name = data.stNm,
            firValue = data.stId, secValue = data.arsId, tag = ""
        )
}