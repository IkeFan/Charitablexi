package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       AboutPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/11 0011
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutPresenter @Inject constructor():IPresenter<IView>() {
    fun loadData(){
        mM.request {
            call = mApi.getAbout()
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
}