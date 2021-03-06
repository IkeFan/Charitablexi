package com.mmy.charitablexi.model.personal.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       PartakeModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/29 0029
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class PartakeModule(val iView: IView) {
    @Provides fun provideView()=iView
}