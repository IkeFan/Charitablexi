package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.component.DaggerInteractComponent
import com.mmy.charitablexi.model.commun.module.InteractModule
import com.mmy.charitablexi.model.commun.presenter.InteractPresenter
import com.mmy.charitablexi.model.commun.ui.activity.PublicCardActivity
import com.mmy.charitablexi.model.commun.ui.adapter.IrrigationAdapter
import com.mmy.frame.AppComponent
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.base.view.BaseFragment
import com.mmy.frame.data.bean.InteractBean
import kotlinx.android.synthetic.main.fragment_trani.*

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
class InteractionFragment :BaseFragment<InteractPresenter>(), CommunFragment.OnPopMenuClick {
    override fun onMenuSelected(v: View) {
        when (v.id) {
            R.id.v_publish -> {
                openActivity(PublicCardActivity::class.java)
            }
            R.id.v_edit -> {

            }
            R.id.v_del -> {

            }
            R.id.v_share -> {

            }
        }
    }

    override fun requestSuccess(any: Any) {
        if(any is InteractBean)
            mAdapter.setNewData(any.data)
    }

    val mAdapter = IrrigationAdapter(R.layout.adapter_irrigation)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerInteractComponent.builder()
                .appComponent(appComponent)
                .interactModule(InteractModule(this))
                .build()
                .inject(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_irrigation

    override fun initView() {
        v_list.layoutManager = LinearLayoutManager(activity)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mIPresenter.getList(3)
    }

    override fun initEvent() {
        arrayOf(v_select_all).setViewListener(this)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, baseViewHolder, position ->

        }
    }
}