package com.mmy.charitablexi.model.personal.module

import com.mmy.charitablexi.model.personal.view.AgreedManagerView
import dagger.Module
import dagger.Provides

/**
 * @file       AgreedManagerModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class AgreedManagerModule(val view: AgreedManagerView) {
    @Provides fun provideView() = view
}