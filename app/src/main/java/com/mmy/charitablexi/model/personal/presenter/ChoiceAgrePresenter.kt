package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.ui.activity.AddAgreActivity
import com.mmy.charitablexi.model.personal.view.ChoiceAgreView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.AgreListBean
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       ChoiceAgrePresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceAgrePresenter @Inject constructor() :IPresenter<ChoiceAgreView>() {
    fun getList(){
        mM.request {
            call = mApi.getAgreList()
            _success = {
                if (it is AgreListBean)
                    mV.refreshList(it.data)
            }
        }
    }

    fun addAgre(data: AddAgreActivity.BusAddAgre) {
        mM.request {
            call = mApi.addAgre(mApp.userInfo.id!!,data.title,data.content)
            _success = {
                if (it is IBean){
                    it.info.showToast(mFrameApp)
                    if (it.status==1){
                        getList()
                    }
                }
            }
        }
    }
}