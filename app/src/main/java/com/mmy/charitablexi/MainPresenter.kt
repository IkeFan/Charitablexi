package com.mmy.charitablexi

import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.api.ApiService
import javax.inject.Inject

/**
 * @file       MainPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/12 0012
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MainPresenter @Inject constructor(val apiService: ApiService) : IPresenter<IView>() {

    fun test() {
        mM.request {
            call = apiService.checkVersion()
            _success = {

            }
            _fail = {

            }
        }
    }
}