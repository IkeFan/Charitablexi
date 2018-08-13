package com.mmy.charitablexi.model.login.presenter

import com.mmy.charitablexi.model.login.ui.activity.LoginActivity
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.RegisterBean
import javax.inject.Inject

/**
 * @file       RegisterCompletePresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RegisterCompletePresenter @Inject constructor() :IPresenter<IView>() {
    fun register(phone:String,pwd:String,name:String){
        mM.request {
            call = mApi.register(phone,pwd,pwd,name)
            _success = {
                if (it is RegisterBean){
                    it.info.showToast(mFrameApp)
                    if (it.status==1)
                        mV.openActivity(LoginActivity::class.java)
                }
            }
        }
    }
}