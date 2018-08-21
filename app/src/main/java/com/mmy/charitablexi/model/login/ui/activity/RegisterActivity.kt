package com.mmy.charitablexi.model.login.ui.activity

import android.view.View
import com.mmy.charitablexi.R
import com.mmy.charitablexi.helper.CountDownTask
import com.mmy.charitablexi.model.login.component.DaggerRegisterComponent
import com.mmy.charitablexi.model.login.module.RegisterModule
import com.mmy.charitablexi.model.login.presenter.RegisterPresenter
import com.mmy.charitablexi.model.login.view.RegisterView
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*
/**
* @file       RegisterActivity.kt
* @brief      注册验证码
* @author     lucas
* @date       2018/4/25 0025
* @version
* @par        Copyright (c):
* @par History:
*             version: zsr, 2017-09-23
*/
class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterView, View.OnClickListener, CountDownTask.OnCountDownTaskListener {
    override fun requestSuccess(any: Any) {
    }

    val countTask = CountDownTask(handler, this)

    override fun setupDagger(appComponent: AppComponent) {
        DaggerRegisterComponent.builder()
                .appComponent(appComponent)
                .registerModule(RegisterModule(this))
                .build().inject(this)
    }

    override fun initView() {
        setToolbar("注册")
    }

    override fun initData() {
    }

    override fun initEvent() {
        super.initEvent()
        arrayOf(v_send_code, v_next).setViewListener(this)
    }

    override fun getLayoutID(): Any = R.layout.activity_register


    override fun progress(progress: Int) {
        v_send_code.isClickable = false
        v_send_code.text = "${progress}秒后重发"
    }

    override fun complete() {
        v_send_code.isClickable = true
        v_send_code.text = "获取验证码"
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.v_send_code -> {
                countTask.start()
                if (v_phone.text.isEmpty()) {
                    "请输入手机号".showToast(mFrameApp)
                    return
                }
                mIPresenter.sendCode(v_phone.text.toString().trim())
            }
            R.id.v_next -> {
                if (v_phone.text.isEmpty()) {
                    "请输入手机号".showToast(mFrameApp)
                    return
                }
                if (v_code.text.isEmpty()) {
                    "请输入验证码".showToast(mFrameApp)
                    return
                }
                //校验验证码
                mIPresenter.checkCode(v_phone.text.toString().trim(), v_code.text.toString().trim())
            }
        }
    }

    override fun openNext() {
        openActivity(RegisterCompleteActivity::class.java,"phone=${v_phone.text.toString().trim()}, pwd=${v_pwd.text.toString().trim()}")
    }
}
