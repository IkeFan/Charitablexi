package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.view.SearchSupportView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.SearchSupportBean
import javax.inject.Inject

/**
 * @file       SearchSupportPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SearchSupportPresenter @Inject constructor() : IPresenter<SearchSupportView>() {

    fun search(key: String) {
        mM.request {
            call = mApi.searchSupport(key)
            _success = {
                if (it is SearchSupportBean)
                mV.refreshList(it.data)
            }
        }
    }
}