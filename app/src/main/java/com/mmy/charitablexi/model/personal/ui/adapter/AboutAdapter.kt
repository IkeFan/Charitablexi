package com.mmy.charitablexi.model.personal.ui.adapter

import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.AboutBean
import com.mmy.frame.utils.Config
import jp.wasabeef.glide.transformations.CropCircleTransformation

/**
 * @file       AboutAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/9 0009
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AboutAdapter(id:Int):BaseQuickAdapter<AboutBean.DataBean.TeamsBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: AboutBean.DataBean.TeamsBean) {
            Glide.with(mContext).load(Config.HOST+item.team_img)
                    .asBitmap().transform(CropCircleTransformation(mContext)).into(helper.getView(R.id.v_icon))
        helper.setText(R.id.v_name,item.team_name)
        helper.setText(R.id.v_desc,item.team_desc)
    }
}