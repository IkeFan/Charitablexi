package com.mmy.charitablexi.wxapi

import dagger.Module
import dagger.Provides

/**
 * @file       WXEntryModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class WXEntryModule(val iView: WXEntryView) {
    @Provides fun provideView() = iView
}