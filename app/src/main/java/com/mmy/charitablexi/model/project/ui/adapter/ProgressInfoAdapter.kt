package com.mmy.charitablexi.model.project.ui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import com.mmy.charitablexi.R
import com.mmy.charitablexi.utils.VRData
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder

/**
 * @file       ProgressInfoAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ProgressInfoAdapter(val id:Int):BaseQuickAdapter<Int,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: Int?) {
        val recyclerView = helper?.getView<RecyclerView>(R.id.rv_list)
        val adapter = ProgressInfoImgAdapter(R.layout.adapter_progress_info_img)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(mContext,OrientationHelper.HORIZONTAL,false)
        adapter.setNewData(VRData.getIntData(10))
    }
}