package com.mmy.charitablexi.model.personal.module

import com.mmy.charitablexi.model.personal.view.ChoiceAdvView
import dagger.Module
import dagger.Provides

/**
 * @file       ChoiceTypeModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class ChoiceAdvModule(val view: ChoiceAdvView) {
    @Provides
    fun provideView()=view
}