package com.mmy.charitablexi.base

import com.bumptech.glide.Glide
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.utils.Config
import jp.wasabeef.glide.transformations.CropCircleTransformation

/**
 * @file       BaseListAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
abstract class BaseListAdapter<T, K : BaseViewHolder>(id: Int) : BaseQuickAdapter<T, K>(id) {
    override abstract fun convert(helper: K, item: T)

    fun BaseViewHolder.setSplitImg(id: Int, url: String, isCircle: Boolean = false) {
        val load = Glide.with(mContext).load(Config.HOST + url)
        if (isCircle)
            load.bitmapTransform(CropCircleTransformation(mContext))
        load.into(this.getView(id))
    }
}