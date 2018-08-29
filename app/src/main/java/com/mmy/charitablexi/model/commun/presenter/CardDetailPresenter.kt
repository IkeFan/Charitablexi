package com.mmy.charitablexi.model.commun.presenter

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       CardDetailPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CardDetailPresenter @Inject constructor() : IPresenter<IView>(){
    fun getDetail(cid:Int){
        mM.request {
            call = mApi.getCardDetail(cid)
            _success = {
                if(it is IBean && it.status!=1){
                    it.info.showToast(mFrameApp)
                }else{
                    mV.requestSuccess(it)
                }
            }
        }
    }
}