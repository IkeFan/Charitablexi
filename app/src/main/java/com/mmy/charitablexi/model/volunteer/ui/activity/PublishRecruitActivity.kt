package com.mmy.charitablexi.model.volunteer.ui.activity

import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity

/**
* @file       PublishRecruitActivity.kt
* @brief      发布招聘
* @author     lucas
* @date       2018/4/18 0018
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class PublishRecruitActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("发布招聘")
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any =R.layout.activity_publish_recruit


}
