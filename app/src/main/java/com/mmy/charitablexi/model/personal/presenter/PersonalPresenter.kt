package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       PersonalPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonalPresenter @Inject constructor() :IPresenter<IView>() {

    fun getInfo(){
        mM.request {
            call = mApi.getPersonalInfo()
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
}