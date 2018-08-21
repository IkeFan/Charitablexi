package com.mmy.charitablexi.model.volunteer.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
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

    fun getVorlist(uid: Int){
        mM.request {
            call =  mApi.getVorList(uid)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
}