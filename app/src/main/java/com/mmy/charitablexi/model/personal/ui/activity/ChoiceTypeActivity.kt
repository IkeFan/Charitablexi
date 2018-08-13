package com.mmy.charitablexi.model.personal.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.personal.component.DaggerChoiceTypeComponent
import com.mmy.charitablexi.model.personal.module.ChoiceTypeModule
import com.mmy.charitablexi.model.personal.presenter.ChoiceTypePresenter
import com.mmy.charitablexi.model.personal.ui.adapter.ChoiceTypeAdapter
import com.mmy.charitablexi.model.personal.view.ChoiceTypeView
import com.mmy.charitablexi.model.volunteer.ui.activity.EditActivity
import com.mmy.charitablexi.widget.MyEditView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ChoiceTypeBean
import com.squareup.otto.Subscribe
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
class ChoiceTypeActivity : BaseActivity<ChoiceTypePresenter>(), ChoiceTypeView {
    override fun requestSuccess(any: Any) {
    }

    val mAdapter = ChoiceTypeAdapter(R.layout.adapter_choice_type)
    val tag = System.currentTimeMillis().toString()

    override fun setupDagger(appComponent: AppComponent) {
        DaggerChoiceTypeComponent.builder()
                .appComponent(appComponent)
                .choiceTypeModule(ChoiceTypeModule(this))
                .build().inject(this)
    }

    override fun initView() {
        val title = intent.getStringExtra("title")
        setToolbar(title)
        v_list.layoutManager = LinearLayoutManager(this)
        v_list.adapter = mAdapter
    }

    override fun initData() {
            mIPresenter.getTypeList()
    }

    override fun initEvent() {
        v_add.setOnClickListener {
            openActivity(EditActivity::class.java, "title=新增类型,hint=请输入类型名称,tag=${tag}")
        }
        mAdapter.setOnItemClickListener { adapter, view, baseViewHolder, position ->
            App.instance.mBus.post(adapter.getItem(position) as ChoiceTypeBean.DataBean)
            finish()
        }
    }

    override fun getLayoutID(): Any = R.layout.activity_choice_type

    override fun refreshTypeList(data: MutableList<ChoiceTypeBean.DataBean>) {
        mAdapter.setNewData(data)
    }

    override fun registerBus(): Boolean = true

    @Subscribe
    fun addType(data: MyEditView.DataBean) {
        if (tag == data.tag) {
            mIPresenter.addType(data.text)
        }
    }


}
