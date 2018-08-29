package com.mmy.charitablexi.model.login.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.charitablexi.MainActivity
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.RegisterBean
import okhttp3.MultipartBody
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
    fun register(param: List<MultipartBody.Part>){
        mV.showLoading()
        mM.request {
            call = mApi.register(param)
            _success = {
                mV.hidLoading()
                if (it is RegisterBean){
                    it.info.showToast(mFrameApp)
                    if (it.status==1)
                        mV.openActivity(MainActivity::class.java)
                }
            }
            _fail = {
                ToastUtils.showShort(it.message)
                mV.hidLoading()
            }
        }
    }
}