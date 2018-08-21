package com.mmy.charitablexi.model.login.presenter

import com.blankj.utilcode.util.SPUtils
import com.mmy.charitablexi.App
import com.mmy.charitablexi.MainActivity
import com.mmy.charitablexi.model.login.ui.activity.LoginActivity
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.LoginBean
import javax.inject.Inject

/**
 * @file       SplashPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SplashPresenter @Inject constructor() : IPresenter<IView>() {
    fun autoLogin() {
        val phone = SPUtils.getInstance().getString("phone", "")
        val pwd = SPUtils.getInstance().getString("pwd", "")
        if (phone.isEmpty() || pwd.isEmpty()) {
            mV.openActivity(LoginActivity::class.java)
            mV.finishView()
            return
        }
        mM.request {
            call = mApi.login(phone, pwd)
            _success = {
                if (it is LoginBean) {
                    it.info.showToast(mFrameApp)
                    if (it.status == 1) {
                        App.instance.userInfo.id = it.data.id.toInt()
                        App.instance.userInfo.name = it.data.name
                        App.instance.userInfo.mobile = it.data.mobile
                        App.instance.userInfo.token = it.data.token
                        App.instance.userInfo.lovesum = it.data.lovesum
                        App.instance.userInfo.email = it.data.email
                        App.instance.initRong()
                        mV.finishView()
                        mV.openActivity(MainActivity::class.java)
                    }else{
                        "登录已过期，请重新登录".showToast(mFrameApp)
                        mV.openActivity(LoginActivity::class.java)
                    }
                    mV.finishView()
                }
            }
        }
    }
}