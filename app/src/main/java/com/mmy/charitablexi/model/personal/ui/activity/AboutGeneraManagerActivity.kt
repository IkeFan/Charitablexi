package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerAboutGeneraManagerComponent
import com.mmy.charitablexi.model.personal.module.AboutGeneraManagerModule
import com.mmy.charitablexi.model.personal.presenter.AboutGeneraManagerPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.AboutGeneraManagerAdapter
import com.mmy.charitablexi.widget.SwipeItemLayout
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AboutBean
import kotlinx.android.synthetic.main.activity_about_genera_manager.*

/**
 * @file       AboutGeneraManagerActivity.kt
 * @brief      团队概括管理
 * @author     lucas
 * @date       2018/6/14 0014
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutGeneraManagerActivity : BaseActivity<AboutGeneraManagerPresenter>(), View.OnClickListener {
    val mAdapter = AboutGeneraManagerAdapter(R.layout.adapter_about_genera_manager)

    override fun requestSuccess(data: Any) {
        if (data is AboutBean) {
            mAdapter.setNewData(data.data.teams)
        }
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerAboutGeneraManagerComponent.builder()
                .aboutGeneraManagerModule(AboutGeneraManagerModule(this))
                .appComponent(appComponent)
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("团队概括", rightRes = "新增", rightTextColor = getResColor(R.color.colorPrimary), rightClickListener = this)
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mIPresenter.loadData()
    }

    override fun onRestart() {
        super.onRestart()
        mIPresenter.loadData()
    }

    override fun initEvent() {
        v_list.addOnItemTouchListener(SwipeItemLayout.OnSwipeItemTouchListener(this))
        mAdapter.onDelete = {
            //删除
            mIPresenter.delTeam(it.id.toInt())
        }
        mAdapter.onItemClick = {
            openActivity(TeamEditActivity::class.java, serializableBean = it)
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_about_genera_manager

    override fun onClick(v: View) {
        when (v.id) {
            R.id.toolbar_right -> {//新增
                openActivity(TeamEditActivity::class.java)
            }
            else -> {
            }
        }
    }
}
