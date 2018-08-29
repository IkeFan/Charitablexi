package com.mmy.charitablexi.model.project.ui.adapter

import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.SponsorDetailBean
import com.mmy.frame.utils.Config
import java.text.SimpleDateFormat

/**
 * @file       SupportProAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/27 0027
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SupportProAdapter(var id: Int) : BaseQuickAdapter<SponsorDetailBean.SupportPro, BaseViewHolder>(id) {

    override fun convert(helper: BaseViewHolder?, item: SponsorDetailBean.SupportPro?) {
        helper?.setText(R.id.v_title, item?.name)
        helper?.setText(R.id.v_content, item?.description)
        val format = SimpleDateFormat(mContext.getString(R.string.date_format))
        var dateStr = format.format(item?.addTime)
        helper?.setText(R.id.v_time,dateStr)
        Glide.with(mContext).load(Config.HOST+item?.img)
                .placeholder(R.mipmap.ic_def)
                .error(R.mipmap.ic_def)
                .into(helper?.getView(R.id.v_img))
    }

}