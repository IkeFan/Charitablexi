package com.mmy.charitablexi.model.project.ui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.ProjectProgressBean
import java.text.SimpleDateFormat
import java.util.*

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
class ProgressInfoAdapter(val id:Int):BaseQuickAdapter<ProjectProgressBean.ProjectProgress,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: ProjectProgressBean.ProjectProgress?) {
        val recyclerView = helper?.getView<RecyclerView>(R.id.rv_list)
        val adapter = ProgressInfoImgAdapter(R.layout.adapter_progress_info_img)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(mContext,OrientationHelper.HORIZONTAL,false)
        helper?.setText(R.id.v_name, item?.name)
        helper?.setText(R.id.v_description,item?.title)
        var calendar =  Calendar.getInstance()
        calendar.timeInMillis = item?.uptime!!
        val format = SimpleDateFormat(mContext.getString(R.string.date_format))
        val dateStr = format.format(calendar.time)
        helper?.setText(R.id.v_time, dateStr)
        if(item.photos!=null){
            adapter.setNewData(item.photos)
        }

        helper?.addOnClickListener(R.id.v_edit)
        helper?.addOnClickListener(R.id.v_delete)
    }
}