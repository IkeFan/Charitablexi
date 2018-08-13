package com.mmy.charitablexi.model.volunteer.ui.window

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.mmy.charitablexi.R

/**
 * @file       VolunteerMenuPopup.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/17 0017
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class VolunteerMenuPopup(val context: Context) : PopupWindow(context) {
    var lisenter: (View) -> Unit = {}

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.window_volunteer_menu, null)
        contentView = view
        isFocusable = true
        height = ViewGroup.LayoutParams.WRAP_CONTENT
        width = ViewGroup.LayoutParams.WRAP_CONTENT
        setBackgroundDrawable(ColorDrawable(0x00ffffff))
        view.findViewById<View>(R.id.v_menu1).setOnClickListener(lisenter)
        view.findViewById<View>(R.id.v_menu2).setOnClickListener(lisenter)
        view.findViewById<View>(R.id.v_menu3).setOnClickListener(lisenter)
    }

}