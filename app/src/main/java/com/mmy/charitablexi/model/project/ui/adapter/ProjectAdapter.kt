package com.mmy.charitablexi.model.project.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.ProListBean

/**
 * @file       ProjectAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/13 0013
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProjectAdapter(itemLayoutID: Int, val isGoneCount: Boolean) : BaseListAdapter<ProListBean.DataBean, BaseViewHolder>(itemLayoutID) {
    override fun convert(helper: BaseViewHolder, item: ProListBean.DataBean) {
        helper.setGone(R.id.v_devide, isGoneCount)
        helper.setGone(R.id.count, isGoneCount)
        helper.setText(R.id.v_donate_count, item.yjrc.toString())
        helper.setText(R.id.v_love_count, "${item.yyax ?: "0"}K")
        helper.setText(R.id.v_love_progress, "${item.axjd * 100}%")
        helper.setText(R.id.v_volun_count, item.ygs.toString())
        helper.setText(R.id.v_title, item.title)
        helper.setText(R.id.v_content, item.description)
        helper.setText(R.id.v_address, item.addr)
        helper.setText(R.id.v_type, item.xmlx)
        helper.setSplitImg(R.id.v_icon, item.img)
    }


}