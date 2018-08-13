package com.mmy.charitablexi.model.login.presenter

import com.mmy.charitablexi.model.login.view.RegisterView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       RegisterPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RegisterPresenter @Inject constructor():IPresenter<RegisterView>(){

    fun sendCode(phone:String){
        mM.request {
            call = mApi.sendCode(phone)
            _success={
                if (it is IBean)
                    it.info.showToast(mFrameApp)
            }
        }
    }

    fun checkCode(phone: String, code: String) {
        mM.request {
            call = mApi.checkCode(phone,code)
            _success = {
                if (it is IBean){
                    it.info.showToast(mFrameApp)
//                    if (it.status==1)
                        mV.openNext()
                }
            }
        }
    }
}