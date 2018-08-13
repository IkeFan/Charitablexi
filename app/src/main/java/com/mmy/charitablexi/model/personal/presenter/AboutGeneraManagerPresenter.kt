package com.mmy.charitablexi.model.personal.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       AboutGeneraManagerPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutGeneraManagerPresenter @Inject constructor():IPresenter<IView>() {

    fun loadData(){
        mM.request {
            call = mApi.getAbout()
            _success = {
                mV.requestSuccess(it)
            }
        }
    }

    fun delTeam(id:Int){
        mM.request {
            call=mApi.delTeam(id)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
}