package com.mmy.charitablexi.model.volunteer.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.RecruitDetailBean
import javax.inject.Inject

/**
 * @file       ZpDetailPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/9/4 0004
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ZpDetailPresenter @Inject constructor():IPresenter<IView>(){
    fun getZpDetail(id:Int){
        mM.request {
            call = mApi.zpDetail(id)
            _success = {
                if(it is RecruitDetailBean){
                    mV.requestSuccess(it)
                }
            }
            _fail = {
                it.message?.showToast(mFrameApp)
            }
        }
    }
}