package com.mmy.charitablexi.wxapi

import com.mmy.frame.base.mvp.IView

/**
 * @file       WXEntryView.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
interface WXEntryView :IView {
     fun authComplete()
}