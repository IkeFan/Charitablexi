package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.adapter.IrrigationAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_irrigation.*

/**
 * @file       InteractionFragment.ktt
 * @brief      互动
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class InteractionFragment :BaseFragment<IPresenter<*>>(){
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = IrrigationAdapter(R.layout.adapter_irrigation)

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_irrigation

    override fun initView() {
        v_list.layoutManager = LinearLayoutManager(activity)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }
}