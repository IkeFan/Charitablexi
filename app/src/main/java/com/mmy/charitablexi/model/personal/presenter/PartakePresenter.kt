package com.mmy.charitablexi.model.personal.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       PartakePresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/29 0029
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PartakePresenter @Inject constructor() : IPresenter<IView>() {
    fun getJoinOrgList() {
        mM.request {
            call = mApi.getJoinVolOrg(mFrameApp.userInfo.id!!)
            _success = {
                if (it is IBean && it.status != 1) {
                    it.info.showToast(mFrameApp)
                } else {
                    mV.requestSuccess(it)
                }
            }
            _fail = {
                ToastUtils.showShort(it.message)
            }
        }
    }

    fun getVolEmployList() {
        mM.request {
            call = mApi.getJoinZp(mFrameApp.userInfo.id!!)
            _success = {
                if (it is IBean && it.status != 1) {
                    it.info.showToast(mFrameApp)
                } else {
                    mV.requestSuccess(it)
                }
            }
            _fail = {
                ToastUtils.showShort(it.message)
            }
        }
    }
}