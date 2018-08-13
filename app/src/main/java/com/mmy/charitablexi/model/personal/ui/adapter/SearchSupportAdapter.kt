package com.mmy.charitablexi.model.personal.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.SearchSupportBean

/**
 * @file       SearchSupportAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SearchSupportAdapter(id: Int) : BaseQuickAdapter<SearchSupportBean.DataBean, BaseViewHolder>(id) {
    var mIndex = -1

    override fun convert(helper: BaseViewHolder?, item: SearchSupportBean.DataBean?) {
        helper?.setText(R.id.v_name, item?.name)
        if (mIndex == helper?.adapterPosition)
            helper.setBackgroundColor(R.id.v_root,mContext.resources.getColor(R.color.colorPrimary))
        else
            helper?.setBackgroundColor(R.id.v_root,mContext.resources.getColor(R.color.white))
    }

    fun singleChoice(position: Int) {
        mIndex = position
        notifyDataSetChanged()
    }

}