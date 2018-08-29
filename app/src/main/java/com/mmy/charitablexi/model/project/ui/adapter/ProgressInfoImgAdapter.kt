package com.mmy.charitablexi.model.project.ui.adapter

import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.utils.Config

/**
 * @file       ProgressInfoImgAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProgressInfoImgAdapter(val id:Int):BaseQuickAdapter<String,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        Glide.with(mContext)
                .load(Config.HOST+item)
                .error(R.mipmap.ic_def)
                .placeholder(R.mipmap.ic_def)
                .into(helper?.getView(R.id.img))
    }
}

