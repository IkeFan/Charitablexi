package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.AgreementManagerAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_agreement_manager.*

/**
 * @file       AgreementManagerActivity.kt
 * @brief      协议管理
 * @author     lucas
 * @date       2018/5/24 0024
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AgreementManagerActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    lateinit var type: String
    val mAdapter = AgreementManagerAdapter(R.layout.adapter_agreement_manager)
    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        type = intent.getStringExtra("type")
        if (type == "view") {
            v_add.visibility = View.GONE
            setToolbar("查看我的协议")
        } else
            setToolbar("协议管理")
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initEvent() {
        if (type == "manager")
            v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
    }

    override fun getLayoutID(): Any = R.layout.activity_agreement_manager

}
