package com.mmy.charitablexi.model.volunteer.ui.activity

import android.content.Intent
import com.mmy.charitablexi.R
import com.mmy.frame.AppComponent
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_request_mech.*

/**
* @file       RequestMechActivity.kt
* @brief      申请机构
* @author     lucas
* @date       2018/4/18 0018
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class RequestMechActivity : BaseActivity<IPresenter<*>>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
    }

    override fun initView() {
        setToolbar("机构认证")
    }

    override fun initData() {
    }

    override fun getLayoutID(): Any =R.layout.activity_request_mech


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        v_logo.onActivityResult(requestCode,resultCode,data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
