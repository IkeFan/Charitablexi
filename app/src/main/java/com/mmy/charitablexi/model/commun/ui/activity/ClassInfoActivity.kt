package com.mmy.charitablexi.model.commun.ui.activity

import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import com.mmy.frame.data.bean.ClassBean

/**
 * @file       ClassInfoActivity.ktivity.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ClassInfoActivity : BaseActivity<IPresenter<*>>(){
    var mClass: ClassBean.DataBean? = null

    override fun setupDagger(appComponent: AppComponent) {

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