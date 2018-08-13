package com.mmy.charitablexi.model.personal.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder

/**
 * @file       MechanismManagerAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/24 0024
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MechanismManagerAdapter(id:Int):BaseQuickAdapter<Int,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: Int) {
        helper.setOnClickListener(R.id.v_delete,{
            notifyItemRemoved(helper.adapterPosition)
        })
    }
}