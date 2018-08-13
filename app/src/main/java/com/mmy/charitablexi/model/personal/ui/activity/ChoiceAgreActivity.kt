package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerChoiceAgreComponent
import com.mmy.charitablexi.model.personal.module.ChoiceAgreModule
import com.mmy.charitablexi.model.personal.presenter.ChoiceAgrePresenter
import com.mmy.charitablexi.model.personal.ui.adapter.ChoiceAgreAdapter
import com.mmy.charitablexi.model.personal.view.ChoiceAgreView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.AgreListBean
import com.squareup.otto.Subscribe
import kotlinx.android.synthetic.main.activity_choice_type.*

/**
 * @file       ChoiceAgreActivity.kt
 * @brief      选择协议
 * @author     lucas
 * @date       2018/5/19 0019
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceAgreActivity : BaseActivity<ChoiceAgrePresenter>(), ChoiceAgreView {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = ChoiceAgreAdapter(R.layout.adapter_choice_type)
    var title = ""
    val tag = System.currentTimeMillis().toString()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerChoiceAgreComponent.builder()
                .appComponent(appComponent)
                .choiceAgreModule(ChoiceAgreModule(this))
                .build().inject(this)
    }

    override fun initView() {
        title = intent.getStringExtra("title")
        setToolbar(title)
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
        mIPresenter.getList()
    }

    override fun initEvent() {
        v_add.setOnClickListener {
            //新增
            openActivity(AddAgreActivity::class.java)
        }
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            if (title == "选择协议") {
                //返回数据
                App.instance.mBus.post(adapter.getItem(position) as AgreListBean.DataBean)
                finish()
            }
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_choice_agre

    override fun refreshList(data: MutableList<AgreListBean.DataBean>) {
        mAdapter.setNewData(data)
    }

    override fun registerBus(): Boolean =true

    @Subscribe
    fun onAddAgre(data: AddAgreActivity.BusAddAgre){
        mIPresenter.addAgre(data)
    }
}
