package com.mmy.charitablexi.model.personal.ui.activity

import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity

/**
 * @file       PrivateMsgActivity.kt
 * @brief      私聊
 * @author     lucas
 * @date       2018/5/17 0017
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PrivateMsgActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        val intent = intent
        val data = intent.data
        val title = data.getQueryParameter("title")
        setToolbar(title)
    }

    override fun initData() {

    }

    override fun getLayoutID(): Any = R.layout.activity_privide_msg

}
