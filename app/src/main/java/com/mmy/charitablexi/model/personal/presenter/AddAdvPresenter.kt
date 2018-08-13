package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       AddAdvPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddAdvPresenter @Inject constructor() : IPresenter<IView>() {
    fun submit(parts: MutableList<MultipartBody.Part>) {
        mM.request {
            call = mApi.addAdv(parts)
            _success = {
                if (it is IBean) {
                    it.info.showToast(mFrameApp)
                    if (it.status == 1)
                        finishActivity()
                }
            }
        }
    }
}