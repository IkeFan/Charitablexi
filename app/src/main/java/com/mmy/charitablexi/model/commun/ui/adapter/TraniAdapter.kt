package com.mmy.charitablexi.model.commun.ui.adapter

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.ClassBean
import com.mmy.frame.utils.Config
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
class TraniAdapter(id: Int):BaseQuickAdapter<ClassBean.DataBean,BaseViewHolder>(id) {
    var mEdit:Boolean = false
    var isSelectAll = false
    var mDelCache = ArrayList<ClassBean.DataBean>()

    override fun convert(helper: BaseViewHolder?, item: ClassBean.DataBean?) {
        helper?.setText(R.id.class_name, item?.title)
        if(item?.imgs!=null ){
            var images = item?.imgs?.split(",")
            Glide.with(mContext).load(Config.HOST + images!![0])
                    .error(R.mipmap.ic_def)
                    .placeholder(R.mipmap.ic_def)
                    .into(helper?.getView(R.id.class_imv) as ImageView)
        }
        helper?.setVisible(R.id.class_cb, mEdit)
        helper?.setChecked(R.id.class_cb, mDelCache.contains(item)|| isSelectAll)
        helper?.getView<View>(R.id.main)?.setOnClickListener {
            if(mEdit){
                val checkBox = helper?.getView<CheckBox>(R.id.class_cb)
                checkBox?.isChecked = !checkBox?.isChecked!!
                if(checkBox.isChecked && !mDelCache.contains(item)){
                    mDelCache.add(item!!)
                }else{
                    mDelCache.remove(item)
                }
            }else{
                onEditArticle(helper.layoutPosition, item!!)
            }

        }
    }

    fun changeEdit(){
        mEdit=!mEdit
        if(!mEdit){
            resetStatus()
        }
        notifyDataSetChanged()
    }

    var onEditArticle:(Int, ClassBean.DataBean)->Unit = {position, item -> }

    fun changeSelectAll(){
        isSelectAll = !isSelectAll
        if(isSelectAll){
            mDelCache.clear()
            mDelCache.addAll(mData)
        }else{
            mDelCache.clear()
        }
        notifyDataSetChanged()
    }

    fun delSelected(){
        if(isSelectAll){
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
        mEdit = false
        isSelectAll = false
        mDelCache.clear()
        notifyDataSetChanged()
    }
}