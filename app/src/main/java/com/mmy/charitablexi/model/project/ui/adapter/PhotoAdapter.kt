package com.mmy.charitablexi.model.project.ui.adapter

import com.mmy.charitablexi.R
import com.mmy.charitablexi.base.BaseListAdapter
import com.mmy.frame.adapter.BaseViewHolder

/**
 * @file       PhotoAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/27 0027
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PhotoAdapter(var layoutId:Int) : BaseListAdapter<String, BaseViewHolder>(layoutId){
    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setSplitImg(R.id.img,item, false)
    }

}