package com.mmy.charitablexi.model.commun.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.ClassBean
import com.mmy.frame.utils.Config

/**
 * @file       TraniAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class TraniAdapter(id: Int):BaseQuickAdapter<ClassBean.DataBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: ClassBean.DataBean?) {
        helper?.setText(R.id.class_name, item?.title)
        if(item?.imgs!=null ){
            var images = item?.imgs?.split(",")
            Glide.with(mContext).load(Config.HOST + images!![0])
                    .error(R.mipmap.ic_def)
                    .placeholder(R.mipmap.ic_def)
                    .into(helper?.getView(R.id.class_imv) as ImageView)
        }

    }
}