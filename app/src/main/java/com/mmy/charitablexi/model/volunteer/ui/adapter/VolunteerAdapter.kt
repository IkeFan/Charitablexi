package com.mmy.charitablexi.model.volunteer.ui.adapter

import android.view.View
import android.widget.CheckBox
import com.mmy.charitablexi.App
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.OrganizationBean
import java.util.*

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
class VolunteerAdapter(val id:Int):BaseQuickAdapter<OrganizationBean.Organization,BaseViewHolder>(id) {
    private var isSelectALL = false
    var mChoseCache = ArrayList<OrganizationBean.Organization>()
    override fun convert(helper: BaseViewHolder?, item: OrganizationBean.Organization?) {

       when(App.instance.userInfo.type){
           0->{

           }
           1->{
               //删除事件
               helper?.getView<View>(R.id.v_del)?.setOnClickListener {
                   delete(it,helper.adapterPosition)
               }
           }
       }

        //单选
        helper?.getView<View>(R.id.v_cb)?.setOnClickListener {
            val checkBox = helper.getView<CheckBox>(R.id.v_cb)
            if(checkBox.isChecked){
                if(!mChoseCache.contains(item))
                    mChoseCache.add(item!!)
            }else{
                if(mChoseCache.contains(item))
                    mChoseCache.remove(item)
            }
            notifyItemChanged(helper?.layoutPosition)
        }
        helper?.getView<View>(R.id.main)?.setOnClickListener {
            selected(it, helper?.layoutPosition)
        }

        var status = if(item?.status == 1){
            mContext.getString(R.string.in_recruitment)
        }else{
            mContext.getString(R.string.recruitment_finish)
        }
        helper?.setText(R.id.v_num_description, status)

        helper?.getView<CheckBox>(R.id.v_cb)?.isChecked = mChoseCache.contains(item)
        helper?.setText(R.id.v_members, item?.ygs.toString()+mContext.getString(R.string.person))
        helper?.setText(R.id.v_name, item?.name)
    }

    //全选
    fun setSelectAll(boolean: Boolean){
        isSelectALL = boolean
        if(isSelectALL){
            mChoseCache.clear()
            mChoseCache.addAll(mData)
        }else{
            mChoseCache.clear()
        }
        notifyDataSetChanged()
    }

    var delete:(View, Int)->Unit={view, position ->  }
    var selected:(View, Int)->Unit={view, position ->  }
}