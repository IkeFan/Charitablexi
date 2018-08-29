package com.mmy.charitablexi.model.commun.ui.adapter

import android.content.Intent
import android.view.View
import android.widget.CheckBox
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.charitablexi.model.commun.ui.activity.PublicCardActivity
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.PersonalCenterBean
import com.mmy.frame.utils.Config
import jp.wasabeef.glide.transformations.CropCircleTransformation
import java.util.*

/**
 * @file       TraniAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class PersonCenterAdapter(id: Int) : BaseQuickAdapter<PersonalCenterBean.PersonalCenter, BaseViewHolder>(id) {
    var isSelectALL = false
    var editMode = false
    var mDelCache = ArrayList<PersonalCenterBean.PersonalCenter>()


    override fun convert(helper: BaseViewHolder?, item: PersonalCenterBean.PersonalCenter?) {
        helper?.setText(R.id.v_name, item?.name)
        Glide.with(mContext).load(Config.HOST + item?.avatar)
                .asBitmap()
                .placeholder(mContext.getDrawable(R.mipmap.ic_vol_icon))
                .override(100,100)
                .transform(CropCircleTransformation(mContext))
                .into(helper?.getView(R.id.v_avatar))
        helper?.setText(R.id.v_title, item?.title)
        helper?.setText(R.id.content, item?.content)

        helper?.setVisible(R.id.v_cb, editMode)
        helper?.setVisible(R.id.v_edit, editMode)
        helper?.setChecked(R.id.v_cb,mDelCache.contains(item)||isSelectALL)
        helper?.getView<View>(R.id.main)?.setOnClickListener {
            val checkBox = helper.getView<CheckBox>(R.id.v_cb)
            checkBox?.isChecked = !checkBox?.isChecked!!
            if(checkBox.isChecked && !mDelCache.contains(item)){
                mDelCache.add(item!!)
            }else{
                mDelCache.remove(item)
            }
        }

        helper?.getView<View>(R.id.v_edit)?.setOnClickListener {
            var intent = Intent()
            intent.setClass(mContext, PublicCardActivity::class.java)
            intent.putExtra("item", item)
            intent.putExtra("position", helper?.layoutPosition)
            mContext.startActivity(intent)
        }
    }

    fun changeSelectAll(){
        isSelectALL = !isSelectALL
        if(isSelectALL){
            mDelCache.clear()
            mDelCache.addAll(mData)
        }else{
            mDelCache.clear()
        }
        notifyDataSetChanged()
    }

    fun delSelected(){
        if(isSelectALL){
            mData.clear()
            resetStatus()
        }else{
            mData.removeAll(mDelCache)
            if(mData.isEmpty()){
                resetStatus()
            }else{
                mDelCache.clear()
            }
        }
        notifyDataSetChanged()
    }

    fun resetStatus(){
        editMode = false
        isSelectALL = false
        mDelCache.clear()
        notifyDataSetChanged()
    }

    fun changeEditMode(){
        editMode=!editMode
        if(!editMode){
            resetStatus()
        }
        notifyDataSetChanged()
    }
}