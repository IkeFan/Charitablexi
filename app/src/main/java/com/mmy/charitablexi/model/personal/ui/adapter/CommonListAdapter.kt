package com.mmy.charitablexi.model.personal.ui.adapter

import android.view.View
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.CommonProjectBean
import com.mmy.frame.utils.Config

/**
 * @file       CollectionListAdaptertAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/23 0023
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CommonListAdapter(id:Int):BaseQuickAdapter<CommonProjectBean.DataBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: CommonProjectBean.DataBean) {
        helper.getView<View>(R.id.count)?.visibility = View.GONE
        helper.getView<View>(R.id.v_devide)?.visibility = View.GONE
        Glide.with(mContext).load(Config.HOST+item.img).into(helper.getView(R.id.v_icon))
        helper.setText(R.id.v_type,item.name)
        helper.setText(R.id.v_address,item.addr)
        helper.setText(R.id.v_title,item.title)
        helper.setText(R.id.v_content,item.description)
    }
}