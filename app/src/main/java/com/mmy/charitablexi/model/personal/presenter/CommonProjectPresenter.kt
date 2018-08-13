package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.App
import com.mmy.charitablexi.model.personal.ui.activity.CommonProjectActivity
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       CommonProjectPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CommonProjectPresenter @Inject constructor() : IPresenter<IView>() {

    fun getList(type: CommonProjectActivity.CommonType) {
        when (type) {
            CommonProjectActivity.CommonType.COLLECTION -> {
                mM.request {
                    call = mApi.getCollection(App.instance.userInfo.id!!)
                    _success = {
                        mV.requestSuccess(it)
                    }
                }
            }
            CommonProjectActivity.CommonType.DONATION -> {
                mM.request {
                    call = mApi.getDonated(App.instance.userInfo.id!!)
                    _success = {
                        mV.requestSuccess(it)
                    }
                }
            }
            CommonProjectActivity.CommonType.JOIN -> {
                mM.request {
                    call = mApi.getVoList(App.instance.userInfo.id!!)
                    _success = {
                        mV.requestSuccess(it)
                    }
                }
            }
        }
    }
}