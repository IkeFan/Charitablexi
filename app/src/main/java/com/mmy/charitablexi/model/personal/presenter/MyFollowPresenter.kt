package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.App
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import javax.inject.Inject

/**
 * @file       MyFollowPresenter.kt@brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MyFollowPresenter @Inject constructor() : IPresenter<IView>() {
    val FOLLOW =1
    val FOLLOWER = 2
    val ORGANIZATION =3

    fun getList(type: Int) {
        val id = App.instance.userInfo.getIdCheckLogin()
        mM.request {
             when (type) {
                FOLLOW -> call = mApi.getMyFollow(id = id)
                FOLLOWER ->  call =mApi.getMyFollow(bid = id)
                ORGANIZATION-> call = mApi.getMyFollow(oid = id)
            }
            _success = {
                mV.requestSuccess(it)
            }
        }

    }
}