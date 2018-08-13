package com.mmy.charitablexi.model.project.ui.window

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mmy.charitablexi.R
import com.mmy.frame.base.view.BasePopup

/**
 * @file       SelectPopupt
 * @brief      选择器
 * @author     lucas
 * @date       2018/5/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class SelectPopup(context: Context) : BasePopup(context) {

    @SuppressLint("WrongConstant")
    override fun initView(mRootView: View?) {
        mRootView?.setOnClickListener {
            dismiss()
        }
    }

    fun setNewData(data: List<String>) {
        val group = mRootView.findViewById<ViewGroup>(R.id.v_container)
        group.removeAllViews()
        data.forEach {
            val textView = TextView(context)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            textView.layoutParams = params
            textView.gravity = Gravity.CENTER
            textView.text = it
            if (data.indexOf(it) != data.size - 1)
                textView.setBackgroundResource(R.drawable.layer_devide)
            textView.setPadding(0, 20, 0, 20)
            textView.setOnClickListener({
                itemOnClick(it)
            })
            group.addView(textView)
        }
    }

    fun setTitle(title:String){
        mRootView.findViewById<TextView>(R.id.v_title).text = title
    }


    var itemOnClick:(View)->Unit = {}


    override fun getLayoutId(): Int = R.layout.popup_select
}