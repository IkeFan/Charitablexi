package com.mmy.charitablexi.model.login.ui.activity

import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
* @file       ForgetActivity.kt
* @brief      忘记密码
* @author     lucas
* @date       2018/5/24 0024
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class ForgetActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
        v_next.setOnClickListener {
            openActivity(Forget2Activity::class.java)
        }
    }

    override fun getLayoutID(): Any =R.layout.activity_forget

}
