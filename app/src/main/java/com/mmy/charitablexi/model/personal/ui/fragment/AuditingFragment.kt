package com.mmy.charitablexi.model.personal.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.ui.adapter.AuditingAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import kotlinx.android.synthetic.main.view_recyclerview.*

/**
 * @file       AuditingFragment.kt
 * @brief      审核
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AuditingFragment:BaseFragment<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = AuditingAdapter(R.layout.adapter_auditing)
    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.view_recyclerview

    override fun initView() {
        v_list.layoutManager = LinearLayoutManager(activity)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(7))
    }
}