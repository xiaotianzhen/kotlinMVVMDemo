package com.quyangyu.mvvmdemo.viewModel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.quyangyu.mvvmdemo.model.ArticalModel
import com.quyangyu.mvvmdemo.repository.ArticalRepository

class ArticalViewModel(application: Application) :AndroidViewModel(application) {

    private var repository:ArticalRepository?=null
    private var data:MutableLiveData<ArticalModel>?=null

    init {
        repository=ArticalRepository(application)
        data= repository?.getLiveDta()
    }

    fun getData():MutableLiveData<ArticalModel>?{
        return data
    }


    fun  requestData(){
        repository?.getHttpData()
    }
}