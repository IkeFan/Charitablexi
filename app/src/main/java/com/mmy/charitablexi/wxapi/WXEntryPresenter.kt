package com.mmy.charitablexi.wxapi

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.WXBean
import com.mmy.frame.data.bean.WXUserInfoBean
import javax.inject.Inject

/**
 * @file       WXEntryPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */

class WXEntryPresenter @Inject constructor(val wxEntryView: WXEntryView) : IPresenter<WXEntryView>() {

    fun getTokenOpenid(code: String) {

        mM.request {
            call = mApi.getTokenOpenid(code)
            _success = {
                if (it is WXBean)
                //获取用户个人信息
                    getWxUserInfo(it.access_token, it.openid)
            }
        }
    }

    fun getWxUserInfo(token: String, openid: String) {
        mM.request {
            call = mApi.getWXUserInfo(token, openid)
            _success = {
                if (it is WXUserInfoBean)
                    mApp.mBus.post(it)
            }
        }
    }
}