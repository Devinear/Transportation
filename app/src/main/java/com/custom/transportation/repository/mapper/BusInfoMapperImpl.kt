package com.custom.transportation.repository.mapper

import com.custom.transportation.common.ConvertUtil
import com.custom.transportation.repository.BookmarkData
import com.custom.transportation.repository.BusInfoData

object BusInfoMapperImpl : Mapper<BusInfoData, BookmarkData> {

    override fun toBookmark(data: BusInfoData): BookmarkData =
        BookmarkData(
            key = -1, isBusInfo = true, name = data.name,
            firValue = ConvertUtil.toBusType(data.thisType), secValue = ConvertUtil.toRouteType(data.routeType)
    )
}