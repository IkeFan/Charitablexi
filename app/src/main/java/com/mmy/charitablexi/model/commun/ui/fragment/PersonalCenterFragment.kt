package com.mmy.charitablexi.model.commun.ui.fragment

import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.adapter.TraniAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment

/**
 * @file       OpenClassFragment.ktt.kt
 * @brief      个人中心
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonalCenterFragment : BaseFragment<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = TraniAdapter(R.layout.adapter_trani)
    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_trani

    override fun initView() {
//        v_list.layoutManager = LinearLayoutManager(activity)
//        v_list.adapter = mCollectionAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }
}