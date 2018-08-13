package com.mmy.charitablexi.model.project.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       RaisingModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class RaisingModule(val iView: IView) {
    @Provides fun provideView() = iView
}