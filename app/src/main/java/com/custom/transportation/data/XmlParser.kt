package com.custom.transportation.data

import android.content.Context
import com.custom.transportation.R
import com.custom.transportation.data.unit.BusInfoData
import com.custom.transportation.data.unit.BusInfoDatabase
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.ui.common.ParserListener
import com.custom.transportation.ui.common.TagBusInfo
import com.custom.transportation.ui.common.TagBusStop
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.ByteArrayInputStream

class XmlParser(val context: Context) {

    fun parseByBusStop(response: String, listener: ParserListener?) {
        try {
            val parser : XmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
            parser.setInput(ByteArrayInputStream(response.toByteArray()), "UTF-8")
            parser.next()

            BusStopDatabase.clear()
            var builder = BusStopData.Builder(0)

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_TAG) {
                    when(parser.name) {
                        TagBusStop.Item.tag -> {
                            builder = BusStopData.Builder(BusStopDatabase.count())
                        }
                        TagBusStop.ID.tag -> {
                            parser.next()
                            builder.setArsId(parser.text.toInt())
                        }
                        TagBusStop.PosX.tag -> {
                            parser.next()
                            builder.setPosX(parser.text.toFloat())
                        }
                        TagBusStop.PosY.tag -> {
                            parser.next()
                            builder.setPosY(parser.text.toFloat())
                        }
                        TagBusStop.StationID.tag -> {
                            parser.next()
                            builder.setStId(parser.text.toInt())
                        }
                        TagBusStop.StationNum.tag -> {
                            parser.next()
                            builder.setStNm(parser.text)
                        }
                        TagBusStop.TmX.tag -> {
                            parser.next()
                            builder.setTmX(parser.text.toFloat())
                        }
                        TagBusStop.TmY.tag -> {
                            parser.next()
                            builder.setTmY(parser.text.toFloat())
                        }
                    }
                }
                else if(eventType == XmlPullParser.END_TAG) {
                    if(parser.name.compareTo(TagBusStop.Item.tag, false) == 0)
                        BusStopDatabase.add(builder.build())
                }
                eventType = parser.next()
            }
            listener?.onParserSuccess()
        }
        catch (e: Exception) {
            listener?.onParserFail()
        }
    }

    fun parseByBusInfo(response: String, listener: ParserListener?) {
        try {
            val parser : XmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
            parser.setInput(ByteArrayInputStream(response.toByteArray()), "UTF-8")
            parser.next()

            BusInfoDatabase.clear()
            var builder = BusInfoData.Builder(0)

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if(eventType == XmlPullParser.START_TAG) {
                    when(parser.name) {
                        TagBusInfo.Item.tag -> {
                            builder = BusInfoData.Builder(BusInfoDatabase.count())
                        }
                        TagBusInfo.RouteNum.tag -> {
                            parser.next()
                            builder.setName(parser.text)
                        }
                        TagBusInfo.FirBus.tag -> {
                            parser.next()
                            builder.setTime(parser.text)
                        }
                        TagBusInfo.SecBus.tag -> {
                            parser.next()
                            builder.setAfter(parser.text)
                        }
                        TagBusInfo.Direction.tag -> {
                            parser.next()
                            builder.setDirection("${parser.text} 방향")
                        }
                        TagBusInfo.BusType.tag -> {
                            parser.next()
                            when(parser.text.toInt()) {
                                1 -> {builder.setBefore(context.getString(R.string.bus_low_floor))}
                                2 -> {builder.setBefore(context.getString(R.string.bus_bendy))}
                                else -> {builder.setBefore(context.getString(R.string.bus_common))}
                            }
                        }
                    }
                }
                else if(eventType == XmlPullParser.END_TAG) {
                    if(parser.name.compareTo(TagBusInfo.Item.tag, false) == 0)
                        BusInfoDatabase.add(builder.build())
                }
                eventType = parser.next()
            }
            listener?.onParserSuccess()
        }
        catch (e: Exception) {
            listener?.onParserFail()
        }
    }
}