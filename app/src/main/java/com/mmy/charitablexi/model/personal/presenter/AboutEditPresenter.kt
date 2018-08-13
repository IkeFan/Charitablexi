package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       AboutEditPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutEditPresenter @Inject constructor():IPresenter<IView>() {

    fun submit(parts: MutableList<MultipartBody.Part>) {
        mM.request {
            call = mApi.editAbout(parts)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }

    fun loadData(){
        mM.request {
            call = mApi.getAbout()
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
}