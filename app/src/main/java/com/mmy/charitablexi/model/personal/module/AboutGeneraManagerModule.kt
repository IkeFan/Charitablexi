package com.mmy.charitablexi.model.personal.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       AboutGeneraManagerModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class AboutGeneraManagerModule(val iView: IView) {
    @Provides fun provideView()=iView
}