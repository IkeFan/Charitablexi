package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.view.ChoiceTypeView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.ChoiceTypeBean
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       ChoiceTypePresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceTypePresenter @Inject constructor() : IPresenter<ChoiceTypeView>() {
    fun getTypeList() {
        mM.request {
            call = mApi.getTypeList()
            _success = {
                if (it is ChoiceTypeBean)
                    mV.refreshTypeList(it.data)
            }
        }
    }

    fun addType(text: String) {
        mM.request {
            call = mApi.addType(text)
            _success = {
                if (it is IBean) {
                    it.info.showToast(mFrameApp)
                    if (it.status == 1)
                        getTypeList()
                }
            }
        }
    }
}