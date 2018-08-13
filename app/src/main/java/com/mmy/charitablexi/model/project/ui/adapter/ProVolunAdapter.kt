package com.mmy.charitablexi.model.project.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.ProInfoBean

/**
 * @file       ProVolunAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/26 0026
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProVolunAdapter(id:Int) :BaseListAdapter<ProInfoBean.DataBean.UsersBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: ProInfoBean.DataBean.UsersBean) {
        helper.setSplitImg(R.id.v_icon,item.avatar,true)
    }
}