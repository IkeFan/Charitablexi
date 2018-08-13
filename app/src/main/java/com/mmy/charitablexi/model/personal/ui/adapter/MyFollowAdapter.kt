package com.mmy.charitablexi.model.personal.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.FollowListBean

/**
 * @file       MyFollowAdapter.kt@brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MyFollowAdapter(id:Int):BaseListAdapter<FollowListBean.DataBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: FollowListBean.DataBean) {
        helper.setSplitImg(R.id.v_icon,item.avatar,true)
        helper.setText(R.id.v_name,item.name)
    }
}