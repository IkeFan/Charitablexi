package com.mmy.charitablexi.model.volunteer.ui.adapter

import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.OrgDetailBean
import com.mmy.frame.utils.Config

/**
 * @file       OrgInfoAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class OrgInfoAdapter(val id:Int):BaseQuickAdapter<OrgDetailBean.Recruit,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: OrgDetailBean.Recruit?) {
        helper?.setText(R.id.item_name, item?.name)
        Glide.with(mContext).load(Config.HOST+item?.photo)
                .error(R.mipmap.ic_def)
                .placeholder(R.mipmap.ic_def)
                .into(helper?.getView(R.id.item_img))

        var status = if(item?.status == 1){
            mContext.getString(R.string.in_recruitment)
        }else{
            mContext.getString(R.string.recruitment_finish)
        }

        helper?.setText(R.id.item_status , status)
        helper?.addOnClickListener(R.id.v_del)
        helper?.addOnClickListener(R.id.main)
    }
}