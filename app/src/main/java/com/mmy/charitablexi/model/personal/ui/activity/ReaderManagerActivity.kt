package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.ReaderManagerAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_reader_manager.*

/**
* @file       QuestionManagerActivity.kt
* @brief      阅读管理
* @author     lucas
* @date       2018/6/5 0005
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class ReaderManagerActivity : BaseActivity<IPresenter<*>>() {
    val mAdapter= ReaderManagerAdapter(R.layout.adapter_question_manager)
    override fun requestSuccess(data: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("题库管理")
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initEvent() {
        v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
        }
        v_add.setOnClickListener {
            //上传图书

        }
    }

    override fun getLayoutID(): Any = R.layout.activity_reader_manager

}