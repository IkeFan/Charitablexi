package com.mmy.charitablexi.model.personal.ui.adapter

import android.view.View
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.AgreListBean

/**
 * @file       AgreementManagerAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/24 0024
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class AgreementManagerAdapter(id:Int):BaseQuickAdapter<AgreListBean.DataBean,BaseViewHolder>(id) {
    override fun convert(helper: BaseViewHolder?, item: AgreListBean.DataBean?) {
        helper?.getView<View>(R.id.v_modify)?.setOnClickListener{
            click(it, helper?.layoutPosition)
        }
        helper?.getView<View>(R.id.v_delete)?.setOnClickListener {
            click(it, helper?.layoutPosition)
        }
        helper?.setText(R.id.v_name,item?.title)
    }

    var click:(View, Int)->Unit={ view, position ->  }
}