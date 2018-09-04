package com.mmy.charitablexi.model.volunteer.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       AddOrEditZpPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/9/1 0001
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddOrEditZpPresenter @Inject constructor(): IPresenter<IView>() {
    fun addZp(list: List<MultipartBody.Part>){
        mV.showLoading()
        mM.request {
            call = mApi.addZp(list)
            _success = {
                mV.hidLoading()
                if(it is IBean){
                    if(it.status == 1){
                        mV.requestSuccess(it)
                    }else{
                        it.info.showToast(mFrameApp)
                    }
                }
            }
            _fail = {
                mV.hidLoading()
                it.message?.showToast(mFrameApp)
            }
        }
    }
}