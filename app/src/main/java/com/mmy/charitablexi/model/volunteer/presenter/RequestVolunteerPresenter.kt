package com.mmy.charitablexi.model.volunteer.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
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
    fun submit(id: String, v_email: String?, v_phone: String?, v_age: String, sex: Int) {
//        mM.request {
//            call = mApi.requestVolunteer(v_email, sex, v_age.toInt(), xmid = id.toInt())
//            _success = {
//                mV.requestSuccess(it)
//            }
//        }
    }


}