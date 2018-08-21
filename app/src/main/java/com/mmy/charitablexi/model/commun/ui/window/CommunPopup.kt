package com.mmy.charitablexi.model.commun.ui.window

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.mmy.charitablexi.R
import com.mmy.frame.base.view.BasePopup
/**
 * @file       CommunPopup.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/29 0029
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class CommunPopup( context: Context):BasePopup(context) {
    override fun initView(mRootView: View?) {
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        arrayOf(R.id.v_publish,R.id.v_edit,R.id.v_del,R.id.v_share).forEach {
            mRootView?.findViewById<View>(it)?.setOnClickListener { onPopClick(it) }
        }
    }

    var onPopClick:(View)->Unit = {}

    override fun getLayoutId(): Int = R.layout.popup_commun

    fun showPublish( show: Boolean){
        var visible = View.VISIBLE
        if(!show){
            visible = View.GONE
        }
       mRootView.findViewById<View>(R.id.v_publish).visibility = visible
    }

    fun showEdit(show: Boolean){
        var visible = View.VISIBLE
        if(!show){
            visible = View.GONE
        }
        mRootView.findViewById<View>(R.id.v_edit).visibility = visible
    }

    fun showDel(show: Boolean){
        var visible = View.VISIBLE
        if(!show){
            visible = View.GONE
        }
        mRootView.findViewById<View>(R.id.v_del).visibility = visible
    }
}