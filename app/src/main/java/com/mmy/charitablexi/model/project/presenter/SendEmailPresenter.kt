package com.mmy.charitablexi.model.project.presenter

import com.mmy.charitablexi.bean.VolunteerData
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       SendEmailPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SendEmailPresenter @Inject constructor() : IPresenter<IView>() {
    fun sendCode(phone: String) {
        mM.request {
            call = mApi.sendCode(phone)
            _success = {
                if(it is IBean){
                    it.sub = "sendCode"
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

    fun submit(volunteerData: VolunteerData,code:String) {
        mM.request {
            call = mApi.requestVolunteer(mFrameApp.userInfo.id!!, volunteerData.v_email, volunteerData.sex, volunteerData.v_age, xmid = volunteerData.xmid, oid = volunteerData.oid, code = code)
            _success = {
                if(it is IBean){
                    it.sub = "submit"
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