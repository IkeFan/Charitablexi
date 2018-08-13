package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerPartakeComponent
import com.mmy.charitablexi.model.personal.module.PartakeModule
import com.mmy.charitablexi.model.personal.presenter.PartakePresenter
import com.mmy.charitablexi.model.personal.ui.adapter.PartakeAdapter
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_partake.*

/**
 * @file       PartakeActivity.kt
 * @brief      参与组织
 * @author     lucas
 * @date       2018/5/29 0029
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PartakeActivity : BaseActivity<PartakePresenter>() {
    val mAdapter = PartakeAdapter(R.layout.adapter_volunteer)
    override fun requestSuccess(data: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerPartakeComponent.builder()
                .appComponent(appComponent)
                .partakeModule(PartakeModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("参与组织")
        v_tabs.addTab(v_tabs.newTab().setText("参与的义工组织"))
        v_tabs.addTab(v_tabs.newTab().setText("参与的义工招聘"))
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
        mAdapter.setNewData(VRData.getIntData(10))
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_partake


}
