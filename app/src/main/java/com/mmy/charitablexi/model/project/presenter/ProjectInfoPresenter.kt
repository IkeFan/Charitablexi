package com.mmy.charitablexi.model.project.presenter

import com.mmy.charitablexi.App
import com.mmy.charitablexi.model.project.ui.activity.ProjectInfoActivity
import com.mmy.charitablexi.model.project.ui.activity.ThankGiveLoveActivity
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
            call = mApi.sendLove(pid, count)
            _success = {
                if (it is IBean) {
                    it.info.showToast(mFrameApp)
                    if (it.status == 1)
                        mV.openActivity(ThankGiveLoveActivity::class.java, "star=${count}")
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
        mM.request {
            call = mApi.collectPro(id, ProjectInfoActivity.Type.Raising.value)
            _success = {
                if (it is IBean)
                    it.sub = "collection"
                mV.requestSuccess(it)
            }
        }
    }

    //评论
    fun comment(id: Int, text: String) {
        mM.request {
            call = mApi.comment(id, text, App.instance.userInfo.id!!)
            _success = {
                (it as IBean).sub = "comment"
                mV.requestSuccess(it)
            }
        }
    }
}