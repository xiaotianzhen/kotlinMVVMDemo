package com.quyangyu.mvvmdemo.repository

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import android.os.Message
import com.quyangyu.mvvmdemo.model.ArticalModel
import com.quyangyu.mvvmdemo.other.AppConstant
import com.quyangyu.mvvmdemo.other.OceanUtil

class ArticalRepository(application: Application) {

    private val liveData=MutableLiveData<ArticalModel>()

    init {
        getHttpData()
    }

    fun getLiveDta():MutableLiveData<ArticalModel>{
        return liveData
    }
     fun getHttpData() {
        val params:HashMap<String,Any> = HashMap()
        params["TransCode"] = "030111"
        params["OpenId"] = "123456789"
        OceanUtil.httpRequest(AppConstant.URL,params,
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                if(msg!!.what ==1){
                    val result= msg!!.obj as String
                    OceanUtil.logE(result)
                    liveData.value=OceanUtil.convertData(result)
                }

            }
        })

    }
}