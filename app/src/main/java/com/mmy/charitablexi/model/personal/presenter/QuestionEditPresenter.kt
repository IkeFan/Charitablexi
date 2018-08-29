package com.mmy.charitablexi.model.personal.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       QuestionEditPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/7 0007
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class QuestionEditPresenter @Inject constructor():IPresenter<IView>() {

    fun addQue(para: List<MultipartBody.Part>){
        mV.showLoading()
        mM.request {
            call = mApi.addQuestion(para)
            _success = {
                mV.hidLoading()
                if(it is IBean){
                    if(it.status!=1){
                       it.info.showToast(mApp)
                    }else{
                        mV.requestSuccess(it)
                    }

                }
            }
            _fail = {
                ToastUtils.showShort(it.message)
                mV.hidLoading()
            }
        }
    }
}