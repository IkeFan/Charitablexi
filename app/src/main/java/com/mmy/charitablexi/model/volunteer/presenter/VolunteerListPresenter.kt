package com.mmy.charitablexi.model.volunteer.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       VolunteerListPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerListPresenter @Inject constructor() :IPresenter<IView>(){
    fun getOrgList(uid:Int){
        mM.request {
            call =  mApi.getOrglist(uid)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }

    fun getVorlist(xmid: Int?=null, oid:Int? = null, name:String? = null){
        mM.request {
            call =  mApi.getVorList(mFrameApp.userInfo.id!!, xmid, oid, name)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }

    fun delOrg(id:Int){
        mV.showLoading()
        mM.request {
            call = mApi.delOrg(mFrameApp.userInfo.id!!, id)
            _success = {
                mV.hidLoading()
                if(it is IBean){
                    if( it.status ==1){
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

    fun delVolunteer(id:Int){
        mV.showLoading()
        mM.request {
            call = mApi.delVorlunteer(mFrameApp.userInfo.id!!, id)
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
                it.message?.showToast(mFrameApp)
            }
        }
    }
}