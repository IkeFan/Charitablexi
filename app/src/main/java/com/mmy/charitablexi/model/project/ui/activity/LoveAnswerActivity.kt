package com.mmy.charitablexi.model.project.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.LoveAnswerAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_love_answer.*

/**
* @file       LoveAnswerActivity.kt
* @brief      爱心答题
* @author     lucas
* @date       2018/5/30 0030
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class LoveAnswerActivity : BaseActivity<IPresenter<*>>() {
    val mAdapter = LoveAnswerAdapter(R.layout.adapter_love_answer)

    override fun requestSuccess(data: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("答题")
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(4))
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->

        }

    }

    override fun getLayoutID(): Any =R.layout.activity_love_answer

}
