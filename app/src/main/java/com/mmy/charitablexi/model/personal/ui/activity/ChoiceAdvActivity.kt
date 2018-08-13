package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerChoiceAdvComponent
import com.mmy.charitablexi.model.personal.module.ChoiceAdvModule
import com.mmy.charitablexi.model.personal.presenter.ChoiceAdvPresenter
import com.mmy.charitablexi.model.personal.ui.adapter.ChoiceAdvAdapter
import com.mmy.charitablexi.model.personal.view.ChoiceAdvView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AdvListBean
import kotlinx.android.synthetic.main.activity_choice_type.*

/**
 * @file       ChoiceTypeActivity.kt
 * @brief      选择类型和广告
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceAdvActivity : BaseActivity<ChoiceAdvPresenter>(), ChoiceAdvView {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = ChoiceAdvAdapter(R.layout.adapter_choice_type)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerChoiceAdvComponent.builder()
                .appComponent(appComponent)
                .choiceAdvModule(ChoiceAdvModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("选择广告")
        v_add.setText("新增广告")
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mIPresenter.getAdvList()
    }

    override fun onRestart() {
        super.onRestart()
        mIPresenter.getAdvList()
    }

    override fun initEvent() {
        v_add.setOnClickListener {
            openActivity(AddAdvActivity::class.java)
        }
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            App.instance.mBus.post(adapter.getItem(position) as AdvListBean.DataBean)
            finish()
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_choice_type

    override fun refreshTypeList(data: MutableList<AdvListBean.DataBean>) {
        mAdapter.setNewData(data)
    }

    override fun registerBus(): Boolean = true


}