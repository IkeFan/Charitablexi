package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.ChoiceTypeBean
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       EditProjectPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class EditProjectPresenter @Inject constructor():IPresenter<IView>() {

    fun subimt(parts: MutableList<MultipartBody.Part>) {
        mM.request {
            call = mApi.addProject(parts)
            _success={
                if (it is IBean){
                    it.info.showToast(mFrameApp)
                    if (it.status==1)
                        finishActivity()
                }
            }
        }
    }

    fun getTypeList() {
        mM.request {
            call = mApi.getTypeList()
            _success = {
                if (it is ChoiceTypeBean)
                    mV.requestSuccess(it)
            }
        }
    }
}