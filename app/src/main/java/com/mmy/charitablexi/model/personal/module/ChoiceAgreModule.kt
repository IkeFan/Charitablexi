package com.mmy.charitablexi.model.personal.module

import com.mmy.charitablexi.model.personal.view.ChoiceAgreView
import dagger.Module
import dagger.Provides

/**
 * @file       ChoiceAgreModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class ChoiceAgreModule(val view:ChoiceAgreView) {
    @Provides fun provideView() = view
}