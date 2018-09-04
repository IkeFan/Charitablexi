package com.mmy.charitablexi.model.project.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       ProgressPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/22 0022
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProgressPresenter @Inject constructor(): IPresenter<IView>() {
    fun getProgressList(xmid: Int){
        mM.request {
            call = mApi.getProgressList(mApp.userInfo.id!!, xmid)
            _success = {
                if(it is IBean && it.status!=1){
                    it.info.showToast(mFrameApp)
                }else{
                    mV.requestSuccess(it)
                }

            }
            _fail = {
                ToastUtils.showShort(it.message)
            }
        }
    }

    fun delProcess(id:Int){
        mV.showLoading()
        mM.request {
            call = mApi.delProcess(mFrameApp.userInfo.id!!, id)
            _success = {
                mV.hidLoading()
                if(it is IBean && it.status!=1){
                    it.info.showToast(mFrameApp)
                }else{
                    mV.requestSuccess(it)
                }
            }
            _fail = {
                mV.hidLoading()
                it.message?.showToast(mFrameApp)
            }
        }
    }
}