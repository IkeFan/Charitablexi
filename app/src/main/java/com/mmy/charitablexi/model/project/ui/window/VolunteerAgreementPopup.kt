package com.mmy.charitablexi.model.project.ui.window

import android.content.Context
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.frame.base.view.BasePopup

/**
 * @file       VolunteerAgreementPopup.kt
 * @brief      义工协议
 * @author     lucas
 * @date       2018/5/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerAgreementPopup(context: Context) :BasePopup(context){
    override fun initView(mRootView: View?) {
        isOutsideTouchable = false
        mRootView?.findViewById<View>(R.id.v_cancel)?.setOnClickListener {
            dismiss()
        }
    }

    fun setClick(click:(View)->Unit){
        mRootView.findViewById<View>(R.id.v_conf)?.setOnClickListener(click)
    }

    override fun getLayoutId(): Int = R.layout.popup_volunteer_agreement
}