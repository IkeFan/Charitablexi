package com.mmy.charitablexi.model.personal.module

import com.mmy.charitablexi.model.personal.view.ChoiceSupportView
import dagger.Module
import dagger.Provides

/**
 * @file       ChoiceSupportModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class ChoiceSupportModule(val view:ChoiceSupportView) {
    @Provides fun provideView() = view

}