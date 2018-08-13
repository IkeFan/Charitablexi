package com.mmy.charitablexi.model.login.ui.activity

import android.content.Intent
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.login.component.DaggerSplashComponent
import com.mmy.charitablexi.model.login.module.SplashModule
import com.mmy.charitablexi.model.login.presenter.SplashPresenter
import com.mmy.charitablexi.setpcount.StepService
import com.mmy.frame.AppComponent
import com.mmy.frame.base.view.BaseActivity

/**
 * @file       SplashActivity.kt
 * @brief      启动界面
 * @author     lucas
 * @date       2018/4/25 0025
 * @version
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SplashActivity : BaseActivity<SplashPresenter>() {
    override fun requestSuccess(any: Any) {
    }

    override fun setupDagger(appComponent: AppComponent) {
        DaggerSplashComponent.builder()
                .appComponent(appComponent)
                .splashModule(SplashModule(this))
                .build().inject(this)
    }

    override fun initView() {
    }

    override fun initData() {
        //开启记步服务
        setupService()
        handler.postDelayed({
            //自动登录
            mIPresenter.autoLogin()
        }, 1000)
    }


    override fun getLayoutID(): Any = R.layout.activity_splash

    /**
     * 开启计步服务
     */
    private fun setupService() {
        val intent = Intent(this, StepService::class.java)
        startService(intent)
    }

}
