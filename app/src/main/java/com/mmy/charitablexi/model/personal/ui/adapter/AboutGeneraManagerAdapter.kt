package com.mmy.charitablexi.model.personal.ui.adapter

import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.AboutBean
import com.mmy.frame.utils.Config
import jp.wasabeef.glide.transformations.CropCircleTransformation

/**
 * @file       AboutGeneraManagerAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutGeneraManagerAdapter(id:Int):BaseQuickAdapter<AboutBean.DataBean.TeamsBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: AboutBean.DataBean.TeamsBean) {
        helper.setText(R.id.v_name,item.team_name)
        helper.setText(R.id.v_desc,item.team_desc)
        Glide.with(mContext).load(Config.HOST+item.team_img).asBitmap()
                .transform(CropCircleTransformation(mContext))
                .error(R.mipmap.ic_def_header)
                .placeholder(R.mipmap.ic_def_header)
                .into(helper.getView(R.id.v_icon))
        helper.setOnClickListener(R.id.v_delete,{
            onDelete(item)
            remove(helper.adapterPosition)
            notifyItemRemoved(helper.adapterPosition)
        })
        helper.setOnClickListener(R.id.v_item,{
            onItemClick(item)
        })
    }

    var onItemClick:(AboutBean.DataBean.TeamsBean)->Unit={}

    var onDelete:(AboutBean.DataBean.TeamsBean)->Unit={}
}