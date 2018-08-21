package com.mmy.charitablexi.base

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       BaseIViewModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class BaseIViewModule(var view: IView){
    @Provides fun provideView() = view
}