package com.mmy.charitablexi.model.commun.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.CardetailBean
import java.text.SimpleDateFormat
import java.util.*

/**
 * @file       ProCommentAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/26 0026
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CardCommentAdapter(id:Int):BaseListAdapter<CardetailBean.comment,BaseViewHolder>(id) {

    val format = SimpleDateFormat("MM-dd")

    override fun convert(helper: BaseViewHolder, item: CardetailBean.comment) {
        if(item.avatar!=null) helper.setSplitImg(R.id.v_icon,item.avatar!!,true)
        helper.setText(R.id.v_name,item.name)
        helper.setText(R.id.v_content,item.content)
        helper.setText(R.id.v_time,format.format(Date(item.addtime!!.toLong()*1_000)))
    }
}