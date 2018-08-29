package com.mmy.charitablexi.model.login.presenter

import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.mmy.charitablexi.App
import com.mmy.charitablexi.MainActivity
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.LoginBean
import javax.inject.Inject

/**
 * @file       LoginPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class LoginPresenter @Inject constructor():IPresenter<IView>() {
    fun login(phone:String,pwd:String){
        mM.request {
            call = mApi.login(phone,pwd)
            _success = {
                if (it is LoginBean){
                    it.info.showToast(mFrameApp)
                    if (it.status==1){
                        //缓存信息
                        SPUtils.getInstance().put("phone",phone)
                        SPUtils.getInstance().put("pwd",pwd)
                        App.instance.userInfo.id = it.data.id.toInt()
                        App.instance.userInfo.name = it.data.name
                        App.instance.userInfo.mobile = it.data.mobile
                        App.instance.userInfo.token = it.data.token
                        App.instance.userInfo.lovesum = it.data.lovesum
                        App.instance.userInfo.email = it.data.email
                        App.instance.userInfo.type = it.data.type
                        App.instance.userInfo.userLevel = it.data.level
                        //链接融云
                        App.instance.initRong()
                        mV.finishView()
                        mV.openActivity(MainActivity::class.java)
                    }
                }
            }
            _fail = {
                ToastUtils.showShort(it.message)
            }
        }
    }
}