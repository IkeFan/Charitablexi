package com.mmy.charitablexi.model.commun.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       PersonalCenterModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class PersonalCenterModule(val iView: IView) {
    @Provides fun provideView() = iView
}