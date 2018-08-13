package com.mmy.charitablexi.model.personal.view

import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.ChoiceTypeBean

/**
 * @file       ChoiceTypeView.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
interface ChoiceTypeView :IView{
    fun refreshTypeList(data: MutableList<ChoiceTypeBean.DataBean>)
}