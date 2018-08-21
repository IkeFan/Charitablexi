package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.ui.activity.AddAgreActivity
import com.mmy.charitablexi.model.personal.view.AgreedManagerView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       AgreedManagerPresenterter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AgreedManagerPresenter @Inject constructor():IPresenter<AgreedManagerView>() {
    fun getList(){
        mM.request {
            call = mApi.getAgreList()
            _success = {
                mV.requestSuccess(it)
            }
        }
    }
    fun delAgre(id: Int){
        mM.request {
            call = mApi.delAgre(mApp.userInfo.id!!,id)
            _success = {
                if (it is IBean) {
                   if(it.status ==1) {
                       mV.onDel(it)
                   }else{
                       it.info.showToast(mApp)
                   }
                }
            }
            _fail = {
                if (it is IBean){
                    it.info.showToast(mApp)
                    getList()
                }
            }
        }
    }

    fun addAgre(data: AddAgreActivity.BusAddAgre) {
        mM.request {
            call = mApi.addAgre(mApp.userInfo.id!!,data.title,data.content)
            _success = {
                if (it is IBean){
                    if (it.status==1){
                        mV.onAdd(it)
                    }else{
                        it.info.showToast(mApp)
                    }
                }
            }
            _fail = {
                if (it is IBean){
                    it.info.showToast(mFrameApp)
                }

            }
        }
    }

    fun editAgre(id:Int, title:String, content:String){
        mM.request {
            call = mApi.addAgre(mApp.userInfo.id!!,id, title,content)
            _success = {
                if (it is IBean){
                    if(it.status == 1){
                        mV.onAdd(it)
                    }else{
                        it.info.showToast(mApp)
                    }
                }
            }
            _fail = {
                if (it is IBean){
                    it.info.showToast(mApp)
                    getList()
                }
            }
        }
    }
}