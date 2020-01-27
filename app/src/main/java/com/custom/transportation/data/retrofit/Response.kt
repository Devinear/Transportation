package com.custom.transportation.data.retrofit

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
class ServiceResult {
    @field:Element(name = "msgHeader")
    var msgHeader: MsgHeader? = null
    @field:Element(name = "msgBody")
    var msgBody: MsgBody? = null
}

@Root(name = "msgHeader", strict = false)
class MsgHeader {
    @field:Element(name = "headerCd")
    var headerCd: String = ""
    @field:Element(name = "headerMsg")
    var headerMsg: String = ""
}

@Root(name = "msgBody", strict = false)
class MsgBody {
    @field:ElementList(inline = true, name = "itemList")
    var itemList = mutableListOf<Item>()
}

@Root(name = "itemList", strict = false)
class Item(
    // bus stop
    @field:Element(name = "arsId", required = false)
    var arsId: String = "",
    @field:Element(name = "posX", required = false)
    var posX: String = "",
    @field:Element(name = "posY", required = false)
    var posY: String = "",
    @field:Element(name = "stId", required = false)
    var stId: String = "",
    @field:Element(name = "stNm", required = false)
    var stNm: String = "",
    @field:Element(name = "tmX", required = false)
    var tmX: String = "",
    @field:Element(name = "tmY", required = false)
    var tmY: String = "",

    // bus info
    @field:Element(name = "rtNm", required = false)
    var rtNm: String = "",
    @field:Element(name = "arrmsg1", required = false)
    var arrmsg1: String = "",
    @field:Element(name = "arrmsg2", required = false)
    var arrmsg2: String = "",
    @field:Element(name = "adirection", required = false)
    var adirection: String = "",
    @field:Element(name = "busType1", required = false)
    var busType1: String = ""
)