package com.mmy.charitablexi.model.commun.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       AddOrEdCardPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/20 0020
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddOrEdCardPresenter @Inject constructor():IPresenter<IView>(){
    fun addOrEditCard(param: List<MultipartBody.Part>){
        mM.request {
            mV.showLoading()
            call = mApi.addOrEditCard(param)
            _success = {
                if(it is IBean){
                    it.info.showToast(mApp)
                }
                mV.requestSuccess(it)
                mV.hidLoading()
            }
            _fail = {
                ToastUtils.showShort(it.message)
                mV.hidLoading()
            }
        }
    }
}