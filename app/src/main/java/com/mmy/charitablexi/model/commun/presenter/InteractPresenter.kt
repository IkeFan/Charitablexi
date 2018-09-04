package com.mmy.charitablexi.model.commun.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.InteractBean
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
class InteractPresenter @Inject constructor ():IPresenter<IView>(){
    fun getList(type:Int){
        mM.request {
            call =  mApi.getCardList(type)
            _success ={
                if(it is InteractBean){
                    mV.requestSuccess(it)
                }

            }
        }

    }

    fun delCar(cardId:Int){
        mV.showLoading()
        mM.request {
            call = mApi.delCard(mFrameApp.userInfo.id!!, cardId)
            _success = {
                mV.hidLoading()
                if(it is IBean){
                    if(it.status ==1){
                        mV.requestSuccess(it)
                    }else{
                        it.info.showToast(mFrameApp)
                    }
                }
            }
            _fail = {
                mV.hidLoading()
                it.message?.showToast(mFrameApp)
            }
        }
    }
}