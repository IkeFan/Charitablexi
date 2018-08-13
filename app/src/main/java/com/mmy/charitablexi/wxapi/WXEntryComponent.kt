package com.mmy.charitablexi.wxapi

import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       WXEntryComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(WXEntryModule::class),dependencies = arrayOf(AppComponent::class))
interface WXEntryComponent {
    fun inject(wxEntryActivity: WXEntryActivity)
}