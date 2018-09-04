package com.mmy.charitablexi.model.commun.ui.adapter

import android.view.View
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
    var mEdtCache = ArrayList<ClassBean.DataBean>()

    override fun convert(helper: BaseViewHolder?, item: ClassBean.DataBean?) {
        helper?.setText(R.id.class_name, item?.title)
        if(item?.imgs!=null ){
            Glide.with(mContext).load(Config.HOST + item?.imgs!![0])
                    .error(R.mipmap.ic_def)
                    .placeholder(R.mipmap.ic_def)
                    .into(helper?.getView(R.id.class_imv) as ImageView)
        }

        helper?.getView<View>(R.id.main)?.setOnClickListener {
            if(mEdtCache.contains(item)){

            }else{
                onSelected(helper.layoutPosition, item!!)
            }
        }

        helper?.getView<View>(R.id.main)?.setOnLongClickListener{
            if(!mEdtCache.contains(item)){
                mEdtCache.add(item!!)
            }
            helper.setVisible(R.id.class_edit_container, true)
            true
        }


        helper?.getView<View>(R.id.class_undo)?.setOnClickListener {
            helper.setVisible(R.id.class_edit_container, false)
            if(mEdtCache.contains(item)){
                mEdtCache.remove(item)
            }
        }
        helper?.getView<View>(R.id.class_edit)?.setOnClickListener {
            onEditArticle(helper.layoutPosition, item!!)
        }

        helper?.getView<View>(R.id.class_delete)?.setOnClickListener {
            onDeleted(helper.layoutPosition, item!!)
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
    var onDeleted: (Int, ClassBean.DataBean)->Unit={position, item->}
    var onSelected:(Int, ClassBean.DataBean)->Unit={position, item->}

    fun resetStatus(){
        mEdit = false
        isSelectAll = false
        mEdtCache.clear()
        notifyDataSetChanged()
    }
}