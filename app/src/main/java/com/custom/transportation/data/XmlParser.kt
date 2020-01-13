package com.custom.transportation.data

import android.content.Context
import android.widget.Toast
import com.custom.transportation.data.unit.BusStopData
import com.custom.transportation.data.unit.BusStopDatabase
import com.custom.transportation.ui.common.ParserListener
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.ByteArrayInputStream

class XmlParser(val context: Context) {

    fun parse(response: String, listener: ParserListener?) {
        try {
            var parser : XmlPullParser = XmlPullParserFactory.newInstance().newPullParser()
            parser.setInput(ByteArrayInputStream(response.toByteArray()), "UTF-8")
            parser.next()

            var index : Int = BusStopDatabase.count()
            var builder = BusStopData.Builder(index)

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when(eventType) {
                    XmlPullParser.START_DOCUMENT -> { }
                    XmlPullParser.START_TAG -> {
                        val tag = parser.name
                        if(tag.compareTo("itemList", false) == 0) {
                            builder = BusStopData.Builder(BusStopDatabase.count())
                        }
                        else if(tag.compareTo("arsId", false) == 0) {
                            parser.next()
                            builder.setArsId(parser.text.toInt())
                        }
                        else if(tag.compareTo("posX", false) == 0) {
                            parser.next()
                            builder.setPosX(parser.text.toFloat())
                        }
                        else if(tag.compareTo("posY", false) == 0) {
                            parser.next()
                            builder.setPosY(parser.text.toFloat())
                        }
                        else if(tag.compareTo("stId", false) == 0) {
                            parser.next()
                            builder.setStId(parser.text.toInt())
                        }
                        else if(tag.compareTo("stNm", false) == 0) {
                            parser.next()
                            builder.setStNm(parser.text)
                        }
                        else if(tag.compareTo("tmX", false) == 0) {
                            parser.next()
                            builder.setTmX(parser.text.toFloat())
                        }
                        else if(tag.compareTo("tmY", false) == 0) {
                            parser.next()
                            builder.setTmY(parser.text.toFloat())
                        }
                    }
                    XmlPullParser.END_TAG -> {
                        if(parser.name.compareTo("itemList", false) == 0)
                            BusStopDatabase.add(builder.build())
                    }
                    XmlPullParser.TEXT -> { }
                }
                eventType = parser.next()
            }

            listener?.parserFinish(true)
        }
        catch (e: Exception) {
            listener?.parserFinish(false)
            Toast.makeText(context, "Parse Exception: ${e.toString()}", Toast.LENGTH_SHORT).show()
        }
    }


}