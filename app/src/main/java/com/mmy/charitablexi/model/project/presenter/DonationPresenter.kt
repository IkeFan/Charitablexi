package com.mmy.charitablexi.model.project.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       RaisingPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class DonationPresenter @Inject constructor() : IPresenter<IView>() {
    fun getList(){
        mM.request {
            call = mApi.getProList(4)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
}