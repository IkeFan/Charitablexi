package com.mmy.charitablexi.model.commun.ui.activity

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.charitablexi.base.DaggerBaseComponent
import com.mmy.charitablexi.model.commun.presenter.CardDetailPresenter
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ClassBean

/**
 * @file       CardDetailActivity.ktivity.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CardDetailActivity : BaseActivity<CardDetailPresenter>(){
    var mClass: ClassBean.DataBean? = null

    override fun setupDagger(appComponent: AppComponent) {
        DaggerBaseComponent.builder()
                .appComponent(appComponent)
                .baseIViewModule(BaseIViewModule(this))
                .build()
                .inject(this)

    }

    override fun initView() {
        setToolbar(getString(R.string.edit_class))
        mClass = intent.getSerializableExtra("sBean") as ClassBean.DataBean?
    }

    override fun initData() {

    }

    override fun getLayoutID(): Any= R.layout.activity_class_info

    override fun requestSuccess(data: Any) {

    }
}