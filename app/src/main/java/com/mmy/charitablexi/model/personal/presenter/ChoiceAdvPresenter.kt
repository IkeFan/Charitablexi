package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.view.ChoiceAdvView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.AdvListBean
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
class ChoiceAdvPresenter @Inject constructor() : IPresenter<ChoiceAdvView>() {
    fun getAdvList() {
        mM.request {
            call = mApi.getAdvList()
            _success = {
                if (it is AdvListBean)
                    mV.refreshTypeList(it.data)
            }
        }
    }

}