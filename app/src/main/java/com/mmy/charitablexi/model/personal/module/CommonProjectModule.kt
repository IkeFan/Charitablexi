package com.mmy.charitablexi.model.personal.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       CommonProjectModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class CommonProjectModule(val iView: IView) {
    @Provides fun provideView() = iView
}