package com.mmy.charitablexi.model.project.presenter

import com.blankj.utilcode.util.ToastUtils
import com.mmy.charitablexi.App
import com.mmy.charitablexi.model.project.ui.activity.ProjectInfoActivity
import com.mmy.charitablexi.model.project.view.ProjectInfoView
import com.mmy.frame.base.mvp.IPresenter
import com.mmy.frame.data.bean.IBean
import javax.inject.Inject

/**
 * @file       ProjectInfoPresenter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProjectInfoPresenter @Inject constructor() : IPresenter<ProjectInfoView>() {

    /**
     * 送爱心
     */
    fun sendLove(pid: Int, count: Int) {
        mM.request {
            call = mApi.sendLove(mApp.userInfo.id!!, pid, count)
            _success = {
                if (it is IBean) {
                    if (it.status == 1) {
                      mV.requestSuccess(it)
                    }
                    else {
                        it.info.showToast(mFrameApp)
                    }
                }
                _fail = {
                    ToastUtils.showShort(it.message)
                }
            }
        }
    }

    //获取项目详情
    fun getProInfo(pid: Int) {
        mM.request {
            call = mApi.getProInfo(pid)
            _success = {
                mV.requestSuccess(it)
            }
        }
    }

    //收藏
    fun collection(id: Int) {
        mV.showLoading()
        mM.request {
            call = mApi.collectPro(id, ProjectInfoActivity.Type.Raising.value)
            _success = {
                mV.hidLoading()
                if (it is IBean)
                    it.sub = "collection"
                mV.requestSuccess(it)
            }
            _fail={
                ToastUtils.showShort(it.message)
                mV.hidLoading()
            }
        }
    }

    //评论
    fun comment(id: Int, text: String) {
        mV.showLoading()
        mM.request {
            call = mApi.comment(id, text, App.instance.userInfo.id!!)
            _success = {
                mV.hidLoading()
                (it as IBean).sub = "comment"
                mV.requestSuccess(it)
            }
            _fail = {
                mV.hidLoading()
                ToastUtils.showShort(it.message)
            }
        }
    }
}