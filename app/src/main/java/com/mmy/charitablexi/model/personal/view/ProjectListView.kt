package com.mmy.charitablexi.model.personal.view

import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.ProListBean

/**
 * @file       ProjectListView.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/17 0017
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
interface ProjectListView :IView{
    fun requestResult(proType:Int, data :List<ProListBean.DataBean>)
}