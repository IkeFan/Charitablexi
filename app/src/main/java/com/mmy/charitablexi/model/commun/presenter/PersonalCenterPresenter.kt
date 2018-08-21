package com.mmy.charitablexi.model.commun.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.PersonalCenterBean
import javax.inject.Inject

/**
 * @file       PublicClassPresenterer.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonalCenterPresenter @Inject constructor ():IPresenter<IView>(){
    fun getList(uid:Int){
        mM.request {
            call =  mApi.getCardsPersonal(uid)
            _success ={
                if(it is PersonalCenterBean){
                    mV.requestSuccess(it)
                }

            }
        }

    }
}