package com.mmy.charitablexi.model.commun.ui.adapter

import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.InteractBean
import com.mmy.frame.utils.Config

/**
 * @file       IrrigationAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class IrrigationAdapter(id:Int):BaseQuickAdapter<InteractBean.Interact,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: InteractBean.Interact?) {
        helper?.setText(R.id.v_title, item?.title)
        helper?.setText(R.id.v_author, item?.name)
        helper?.setText(R.id.v_count, item?.cardsCounts.toString())
        if(item?.imgs != null){
            item?.imgs?.split(",")?.forEach{
                Glide.with(mContext).load(Config.HOST+it)
                        .error(R.mipmap.ic_def)
                        .placeholder(R.mipmap.ic_def)
                        .into(helper?.getView(R.id.v_img))
                return
            }
        }
    }
}