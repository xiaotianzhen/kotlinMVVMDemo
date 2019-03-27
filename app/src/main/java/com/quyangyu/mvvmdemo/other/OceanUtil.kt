package com.quyangyu.mvvmdemo.other

import android.os.Handler
import android.os.Message
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.quyangyu.mvvmdemo.model.ArticalModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.collections.HashMap

object OceanUtil {

    private const val TAG: String = "ocean"

    object Holder {
        val OK_HTTP_CLIENT = OkHttpClient()
        val GSON = Gson()
    }

    fun httpRequest(url: String, params: HashMap<String, Any>, handler: Handler) {

        val jsonParams = JSONObject(params)
        val requestBody = RequestBody.create(AppConstant.MEDIA_TYPE_JSON, jsonParams.toString())
        val okHttpClient = Holder.OK_HTTP_CLIENT
        val request = Request.Builder().url(url).post(requestBody).build()
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                logE("请求失败")
            }

            override fun onResponse(call: Call, response: Response) {
                logE("请求成功")
                val result: String = response.body()!!.string().toString()
                val message = Message()
                message.obj = result
                message.what = 1
                handler.sendMessage(message)
            }
        })

    }

    /**
     * 日志打印
     */
    fun logE(any: Any) {
        if (AppConstant.isDegug) {
            Log.e(TAG, "-> -> ->日志打印【$any】")
        }
    }


    /**
     * 数据转换
     */
    fun convertData(result: String): ArticalModel {
        return Holder.GSON.fromJson(result, object : TypeToken<ArticalModel>() {}.type)
    }
}