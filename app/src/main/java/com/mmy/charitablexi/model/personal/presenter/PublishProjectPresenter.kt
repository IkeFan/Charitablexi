package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.view.ProjectListView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.ProListBean
import javax.inject.Inject

/**
 * @file       PublishProjectPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PublishProjectPresenter @Inject constructor():IPresenter<ProjectListView>() {

    fun getPassPro(){
        mM.request {
         call = mApi.getProList(mApp.userInfo.id!!, 3)
            _success={
                if(it is ProListBean) {
                    mV.requestResult(3,data = it.data)
                }
            }
        }
    }

    fun getUnPassPro(){
        mM.request {
            call = mApi.getProList(mApp.userInfo.id!!, 1)
            _success={
                if(it is ProListBean) {
                    mV.requestResult(1,  data = it.data)
                }
            }
        }
    }

}