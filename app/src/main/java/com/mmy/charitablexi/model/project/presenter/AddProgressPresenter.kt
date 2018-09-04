package com.mmy.charitablexi.model.project.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import okhttp3.MultipartBody
import javax.inject.Inject

/**
 * @file       AddProgressPresenter.ktbrief      描述
 * @author     lucas
 * @date       2018/8/29 0029
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AddProgressPresenter @Inject constructor ():IPresenter<IView>() {
    fun addProgress(part:List<MultipartBody.Part>){
        mV.showLoading()
        mM.request {
            call = mApi.addProcess(part)
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
                ToastUtils.showShort(it.message)
            }
        }
    }

    fun editProgress(part:List<MultipartBody.Part>){
        mV.showLoading()
        mM.request {
            call = mApi.editProcess(part)
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
                ToastUtils.showShort(it.message)
            }
        }
    }
}