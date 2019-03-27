package com.quyangyu.mvvmdemo.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.quyangyu.mvvmdemo.R
import com.quyangyu.mvvmdemo.model.ArticalModel
import com.quyangyu.mvvmdemo.viewModel.ArticalViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.databinding.DataBindingUtil
import com.quyangyu.mvvmdemo.BR
import com.quyangyu.mvvmdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var ifFirst:Boolean=true
    private var vm: ArticalViewModel? = null
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(ArticalViewModel::class.java)
        initData()
        /*  var articalModel =ArticalModel()
            var body: ArticalModel.BodyBean=ArticalModel.BodyBean()
            body.img_author="yicooll"
            body.word="www.baidu.com"
            articalModel.body=body*/
    }

    private fun initData() {
        btn_get.setOnClickListener(View.OnClickListener {
            vm?.requestData()
        })

        vm?.getData()?.observe(this, Observer {
            it.let { it1 -> updateView(it1) }
        })
    }

    fun updateView(model: ArticalModel?) {
        if(ifFirst){
            binding?.setVariable(BR.artical, model)
            ifFirst=false
        }else{
            model?.body?.img_author="yicooll"
            binding?.setVariable(BR.artical, model)
        }
        Toast.makeText(this, model?.body?.img_author, Toast.LENGTH_SHORT).show()
    }
}