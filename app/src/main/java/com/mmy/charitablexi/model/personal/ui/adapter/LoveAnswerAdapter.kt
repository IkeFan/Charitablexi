package com.mmy.charitablexi.model.personal.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder

/**
 * @file       LoveAnswerAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/30 0030
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class LoveAnswerAdapter(id:Int):BaseListAdapter<Int,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: Int) {
        if (helper.adapterPosition==0)
            helper.setBackgroundRes(R.id.v_text,R.drawable.selector_answer_true)
    }

    fun answer(){

    }
}