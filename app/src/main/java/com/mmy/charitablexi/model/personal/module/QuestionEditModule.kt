package com.mmy.charitablexi.model.personal.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       QuestionEditModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/7 0007
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class QuestionEditModule(val iView: IView) {
    @Provides
    fun provideView() = iView
}