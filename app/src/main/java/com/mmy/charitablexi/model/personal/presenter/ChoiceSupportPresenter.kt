package com.mmy.charitablexi.model.personal.presenter

import com.mmy.charitablexi.model.personal.ui.activity.SearchSupportActivity
import com.mmy.charitablexi.model.personal.view.ChoiceSupportView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.IBean
import com.mmy.frame.data.bean.SupportListBean
import javax.inject.Inject

/**
 * @file       ChoiceSupportPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceSupportPresenter @Inject constructor() : IPresenter<ChoiceSupportView>() {

    fun getSupportList() {
        mM.request {
            call = mApi.getSupportList()
            _success = {
                if (it is SupportListBean)
                    mV.refreshList(it.data)
            }
        }
    }

    fun addSupport(busSupportBean: SearchSupportActivity.BusSupportBean) {
        mM.request {
            call = mApi.addSupportInPro(busSupportBean.zid, busSupportBean.content)
            _success = {
                if (it is IBean && it.status == 1)
                    getSupportList()
            }
        }
    }

    fun setMainSupport(id: Int?) {
        mM.request {
            call = mApi.setMainSupport(id!!)
            _success ={
                if (it is IBean && it.status==1)
                    it.info.showToast(mFrameApp)
            }
        }
    }
}