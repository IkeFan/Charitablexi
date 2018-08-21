package com.mmy.charitablexi.model.project.ui.adapter

import android.view.View
import android.widget.CheckBox
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.VolunteersBean

/**
 * @file       VolunteerListAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerListAdapter(id: Int) : BaseQuickAdapter<VolunteersBean.Volunteer, BaseViewHolder>(id){


    var isSelectALL = false
    val level= App.instance.userInfo.userLevel


    override fun convert(helper: BaseViewHolder?, item: VolunteersBean.Volunteer?) {
        //判断用户身份
        when (level) {
            0 -> {
                helper?.setGone(R.id.v_info, true)
                helper?.setGone(R.id.v_cb, true)
                helper?.setGone(R.id.v_del, true)
            }
            1 -> {
            }
            2 -> {
                helper?.setGone(R.id.v_info, false)
                helper?.setGone(R.id.v_cb, false)
                helper?.setGone(R.id.v_del, false)
            }
        }
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
        var sex = "男"
        if(item?.sex!=1){
            sex = "女"
        }
        helper?.setText(R.id.v_name, item?.name)
        helper?.setText(R.id.v_age_em, "年龄："+item?.age+" 邮箱 "+item?.email)
        helper?.setText(R.id.v_sex_phone, "性别："+sex+" 电话 "+item?.mobile)
    }

    //全选
    fun selectAll(){
        isSelectALL = !isSelectALL
        notifyDataSetChanged()
    }

    var click:(View, Int)->Unit={view, position ->  }

}