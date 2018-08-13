package com.mmy.charitablexi.model.personal.ui.adapter

import android.view.View
import android.widget.CheckBox
import com.mmy.charitablexi.R
import com.mmy.frame.adapter.BaseQuickAdapter
import com.mmy.frame.adapter.BaseViewHolder
import com.mmy.frame.data.bean.SupportListBean

/**
 * @file       ChoiceSupportAdapter.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class ChoiceSupportAdapter(id: Int) : BaseQuickAdapter<SupportListBean.DataBean, BaseViewHolder>(id) {
    var selectMain = 0

    override fun convert(helper: BaseViewHolder?, item: SupportListBean.DataBean?) {
        helper?.setText(R.id.v_name, item?.name)
        helper?.setText(R.id.v_content, item?.material)
        helper?.getView<View>(R.id.v_set_main)?.setOnClickListener {
            selectMain = helper.adapterPosition
            _onCheckMain(selectMain)
            notifyDataSetChanged()
        }
        helper?.getView<CheckBox>(R.id.v_set_main_cb)?.isChecked = selectMain == helper?.adapterPosition
        helper?.getView<View>(R.id.v_delete)?.setOnClickListener {
            _onDelete(helper.adapterPosition)
            notifyItemRemoved(helper.adapterPosition)
        }
        item?.position = helper?.adapterPosition
    }

    var _onCheckMain: (Int) -> Unit = {}

    var _onDelete: (Int) -> Unit = {}

    fun sortBean() {
        data.sortBy { if (selectMain == it.position) 1 else 0 }
    }

}