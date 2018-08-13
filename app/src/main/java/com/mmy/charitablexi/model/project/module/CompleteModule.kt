package com.mmy.charitablexi.model.project.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       CompleteModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/26 0026
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class CompleteModule(val v:IView) {
    @Provides fun provideView() = v
}