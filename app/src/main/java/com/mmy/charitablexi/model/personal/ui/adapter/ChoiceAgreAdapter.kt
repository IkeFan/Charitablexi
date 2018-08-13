package com.mmy.charitablexi.model.personal.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.AgreListBean

/**
 * @file       ChoiceAgreAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceAgreAdapter(id:Int):BaseQuickAdapter<AgreListBean.DataBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder, item: AgreListBean.DataBean) {
        helper.setText(R.id.v_name,item.title)
    }
}