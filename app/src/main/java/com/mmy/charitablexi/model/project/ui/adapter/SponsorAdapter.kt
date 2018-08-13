package com.mmy.charitablexi.model.project.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.ProInfoBean

/**
 * @file       SponsorAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/26 0026
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SponsorAdapter(id:Int):BaseListAdapter<ProInfoBean.DataBean.ZhfListBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: ProInfoBean.DataBean.ZhfListBean) {
        helper.setSplitImg(R.id.v_icon,item.avatar)
        helper.setText(R.id.v_title,item.name)
        helper.setText(R.id.v_content,item.material)
    }
}