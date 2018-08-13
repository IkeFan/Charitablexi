package com.mmy.charitablexi.model.personal.ui.window

import android.content.Context
import android.view.View
import com.mmy.charitablexi.R
import com.mmy.frame.base.view.BasePopup

/**
 * @file       LoveAnswerPopup.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/30 0030
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class LoveAnswerPopup(context: Context) : BasePopup(context) {
    override fun initView(mRootView: View?) {
        mRootView?.findViewById<View>(R.id.v_del)?.setOnClickListener { dismiss() }
        mRootView?.findViewById<View>(R.id.v_answer)?.setOnClickListener { click(it) }
    }

    var click:(View)->Unit = {}

    override fun getLayoutId(): Int = R.layout.popup_love_answer

}