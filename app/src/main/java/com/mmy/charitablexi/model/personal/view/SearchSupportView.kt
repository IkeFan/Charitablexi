package com.mmy.charitablexi.model.personal.view

import com.mmy.frame.base.mvp.IView
import com.mmy.frame.data.bean.SearchSupportBean

/**
 * @file       SearchSupportView.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
interface SearchSupportView : IView {
    fun refreshList(data: MutableList<SearchSupportBean.DataBean>)
}