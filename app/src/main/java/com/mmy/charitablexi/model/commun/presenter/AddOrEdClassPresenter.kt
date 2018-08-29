package com.mmy.charitablexi.model.commun.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       AddOrEdClassPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddOrEdClassPresenter @Inject constructor() : IPresenter<IView>() {
    fun addOrEditClass(param: List<MultipartBody.Part>) {
        mV.showLoading()
        mM.request {
            call = mApi.addOrEdClass(param)
            _success = {
                if (it is IBean) {
                    if (it.status != 1) {
                        it.info.showToast(mFrameApp)
                    }
                    mV.requestSuccess(it)
                }
                mV.hidLoading()
            }
            _fail = {
                ToastUtils.showShort(it.message)
                mV.hidLoading()
            }
        }
    }
}