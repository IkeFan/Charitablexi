package com.mmy.charitablexi.model.volunteer.ui.adapter

import android.view.View
import android.widget.CheckBox
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder

/**
 * @file       VolunteerAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/17 0017
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerAdapter(val id:Int):BaseQuickAdapter<Int,BaseViewHolder>(id) {
    var isSelectALL = false
    override fun convert(helper: BaseViewHolder?, item: Int?) {
        //删除事件
        helper?.getView<View>(R.id.v_del)?.setOnClickListener {
            click(it,helper.adapterPosition)
        }
        //全选
        helper?.getView<CheckBox>(R.id.v_cb)?.isChecked = isSelectALL
        //单选
        helper?.getView<View>(R.id.main)?.setOnClickListener {
            val checkBox = helper.getView<CheckBox>(R.id.v_cb)
            checkBox?.isChecked = !checkBox?.isChecked!!
        }
    }

    //全选
    fun selectAll(){
        isSelectALL = !isSelectALL
        notifyDataSetChanged()
    }

    var click:(View, Int)->Unit={view, position ->  }
}