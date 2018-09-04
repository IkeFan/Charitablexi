package com.mmy.charitablexi.model.volunteer.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       OrgDetailPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/9/1 0001
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class OrgDetailPresenter @Inject constructor():IPresenter<IView>() {
    fun getOrgDetail(id:Int){
        mM.request {
            call = mApi.orgDetail(mFrameApp.userInfo.id!!, id)
            _success = {
                mV.requestSuccess(it)
            }
            _fail = {
                it.message?.showToast(mFrameApp)
            }
        }
    }

    fun orgAttention(oid:Int, bid:Int, status:Int){
        mV.showLoading()
        mM.request {
            call = mApi.orgAttention(mFrameApp.userInfo.id!!, oid, bid, status)
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

    fun delZp(id:Int){
        mV.showLoading()
        mM.request {
            call = mApi.delZp(mFrameApp.userInfo.id!!, id)
            _success = {
                mV.hidLoading()
                if(it is IBean){
                    mV.requestSuccess(it)
                    if(it.status == 1){

                    }else{
                        it.info.showToast(mFrameApp)
                    }
                }
            }
            _fail = {
                it.message?.showToast(mFrameApp)
            }
        }
    }
}