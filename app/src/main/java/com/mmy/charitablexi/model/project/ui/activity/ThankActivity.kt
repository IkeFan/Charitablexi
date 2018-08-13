package com.mmy.charitablexi.model.project.ui.activity

import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity

/**
 * @file       ThankActivity.kt
 * @brief      致谢
 * @author     lucas
 * @date       2018/5/15 0015
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ThankActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("等待审核")
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any = R.layout.activity_thank


}
