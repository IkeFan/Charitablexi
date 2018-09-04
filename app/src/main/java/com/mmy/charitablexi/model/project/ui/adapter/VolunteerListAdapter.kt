package com.mmy.charitablexi.model.project.ui.adapter

import android.view.View
import android.widget.CheckBox
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.VolunteersBean
import java.util.*

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
class VolunteerListAdapter(id: Int) : BaseQuickAdapter<VolunteersBean.Volunteer, BaseViewHolder>(id) {
    private var isSelectALL = false
    var mChoseCache = ArrayList<VolunteersBean.Volunteer>()


    override fun convert(helper: BaseViewHolder?, item: VolunteersBean.Volunteer?) {
        helper?.setText(R.id.v_name, item?.name)
        //判断用户身份
        when(App.instance.userInfo.type){
            0->{
                helper?.setGone(R.id.v_info, false)
                helper?.setGone(R.id.v_cb, false)
                helper?.setGone(R.id.v_del, false)
            }
            1->{
                helper?.setGone(R.id.v_info, true)
                helper?.setGone(R.id.v_cb, true)
                helper?.setGone(R.id.v_del, true)
                var sex = "男"
                if (item?.sex != 1) {
                    sex = "女"
                }
                helper?.setText(R.id.v_age_em, "年龄：" + item?.age + " 邮箱 " + item?.email)
                helper?.setText(R.id.v_sex_phone, "性别：" + sex + " 电话 " + item?.mobile)
            }
        }

        //删除事件
        helper?.getView<View>(R.id.v_del)?.setOnClickListener {
            delete(it, helper.adapterPosition)
        }

        //单选
        helper?.getView<View>(R.id.main)?.setOnClickListener {
            val checkBox = helper.getView<CheckBox>(R.id.v_cb)
            checkBox?.isChecked = !checkBox?.isChecked!!
            if(checkBox?.isChecked){
                if(!mChoseCache.contains(item))
                    mChoseCache.add(item!!)

            }else{
                if(mChoseCache.contains(item))
                    mChoseCache.remove(item)
            }
        }
        helper?.setChecked(R.id.v_cb, mChoseCache.contains(item))
    }

    //全选
    fun setSelectAll(boolean: Boolean) {
        isSelectALL = boolean
        if(isSelectALL){
            mChoseCache.clear()
            mChoseCache.addAll(mData)
        }else{
            mChoseCache.clear()
        }
        notifyDataSetChanged()
    }
    fun isSelectAll():Boolean = isSelectALL

    var delete: (View, Int) -> Unit = { view, position -> }

}