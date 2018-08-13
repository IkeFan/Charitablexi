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
    fun getList(title: String) {
        val id = App.instance.userInfo.getIdCheckLogin()
        mM.request {
            when (title) {
                "关注" -> {
                    call = mApi.getMyFollow(id = id)
                }
                "关注者" -> {
                    call = mApi.getMyFollow(bid = id)
                }
            }
            _success = {
                mV.requestSuccess(it)
            }
        }

    }
}