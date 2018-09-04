package com.mmy.charitablexi.model.volunteer.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       RequestVolunteerPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/26 0026
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RequestVolunteerPresenter @Inject constructor() : IPresenter<IView>() {
    fun submit(xmid: Int?=null, oid:Int?=null, v_email: String?, v_phone: String?, v_age: String, sex: Int, code:String) {
        mM.request {
            call = mApi.requestVolunteer(mFrameApp.userInfo.id!!, v_email, sex, v_age.toInt(), xmid = xmid, oid = oid, code = code)
            _success = {
                if(it is IBean && it.status ==1)
                    mV.requestSuccess(it)
                else if(it is IBean){
                    it.info.showToast(mFrameApp)
                }
            }
            _fail = {
                ToastUtils.showShort(it.message)
            }
        }
    }


}