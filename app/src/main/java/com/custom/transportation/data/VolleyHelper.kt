package com.custom.transportation.data

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.custom.transportation.ui.common.CallBackUrl
import com.custom.transportation.ui.common.Common
import com.custom.transportation.ui.common.ParserListener
import java.net.URLEncoder

class VolleyHelper(private val context: Context) {

    companion object {
        val TAG = "VolleyHelper"
        private var instance : VolleyHelper? = null
        @JvmStatic fun getInstance(context: Context) : VolleyHelper = instance ?: synchronized(this) {
            instance ?: VolleyHelper(context).also { instance = it }
        }
    }

    private var requestQueue: RequestQueue? = null

    fun requestByName(name: String, listener: ParserListener?) {
        val newName = URLEncoder.encode(name, "utf-8")
        requestQueue = Volley.newRequestQueue(context)
        val url = "${CallBackUrl.getStationByNameList}?ServiceKey=${Common.ServiceKey}&stSrch=${newName}"

        var stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response -> XmlParser(context).parseByBusStop(response, listener) },
            Response.ErrorListener { Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show() })
        stringRequest.tag = TAG
        stringRequest.setShouldCache(false)
        requestQueue?.add(stringRequest)
    }

    fun requestByArsId(arsId: Int, listener: ParserListener?) {
        requestQueue = Volley.newRequestQueue(context)
        val url = "${CallBackUrl.getStationByUidItem}?ServiceKey=${Common.ServiceKey}&arsId=${arsId}"

        var stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener { response -> XmlParser(context).parseByBusInfo(response, listener) },
            Response.ErrorListener { Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show() })
        stringRequest.tag = TAG
        stringRequest.setShouldCache(false)
        requestQueue?.add(stringRequest)
    }

    fun cancelAll() {
        requestQueue?.cancelAll(TAG)
    }
}