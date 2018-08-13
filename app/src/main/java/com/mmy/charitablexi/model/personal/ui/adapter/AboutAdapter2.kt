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
class AboutAdapter2(id: Int) : BaseQuickAdapter<AboutBean.DataBean.LinkmansBean, BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: AboutBean.DataBean.LinkmansBean) {
        Glide.with(mContext).load(Config.HOST + item.link_img)
                .asBitmap().transform(CropCircleTransformation(mContext)).into(helper.getView(R.id.v_icon))
        helper.setText(R.id.v_name, item.link_name)
        helper.setText(R.id.v_desc, "邮箱:${item.link_email}\n电话:${item.link_tel}\n微信:${item.link_wechat}\n")
    }
}