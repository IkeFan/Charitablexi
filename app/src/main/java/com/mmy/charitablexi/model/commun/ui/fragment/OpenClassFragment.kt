package com.mmy.charitablexi.model.commun.ui.fragment

import android.support.v7.widget.GridLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.activity.PublishArticleActivity
import com.mmy.charitablexi.model.commun.ui.adapter.TraniAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_trani.*

/**
 * @file       OpenClassFragment.ktt.kt
 * @brief      公开课
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class OpenClassFragment : BaseFragment<IPresenter<*>>() {
    override fun requestSuccess(data: Any) {
    }

    val mAdapter = TraniAdapter(R.layout.adapter_trani)
    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun getLayoutId(): Int = R.layout.fragment_trani

    override fun initView() {
        v_tabs.addTab(v_tabs.newTab().setText("慈善理论"))
        v_tabs.addTab(v_tabs.newTab().setText("慈善执行"))
        v_tabs.addTab(v_tabs.newTab().setText("其他"))
        v_list.layoutManager = GridLayoutManager(activity, 2)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initEvent() {
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            openActivity(PublishArticleActivity::class.java, "title=专业知识发布")
        }
    }
}