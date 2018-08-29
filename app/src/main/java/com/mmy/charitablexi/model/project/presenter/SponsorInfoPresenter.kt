package com.mmy.charitablexi.model.project.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       SponsorInfoPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/23 0023
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SponsorInfoPresenter @Inject constructor() :IPresenter<IView>(){
    fun getSponsorDetail(sid:Int){
        mM.request {
            call = mApi.getSupportDetail(mApp.userInfo.id!!, sid)
            _success = {
                mV.requestSuccess(it)
            }
            _fail = {
                ToastUtils.showShort(it.message)
            }
        }
    }
}