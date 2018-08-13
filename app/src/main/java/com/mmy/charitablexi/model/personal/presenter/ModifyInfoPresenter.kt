package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       ModifyInfoPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/6 0006
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ModifyInfoPresenter @Inject constructor():IPresenter<IView>() {
    fun submit(build: MutableList<MultipartBody.Part>) {
        mM.request {
            call = mApi.modifyProsnalInfo(build)
            _success={
                mV.requestSuccess(it)
            }
        }
    }
}