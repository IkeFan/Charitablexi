package com.mmy.charitablexi

import com.mmy.frame.base.annotation.ActivityScope
import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       MainModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/12 0012
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Module
class MainModule(val mainView: IView) {
    @Provides fun provideView() = mainView
}