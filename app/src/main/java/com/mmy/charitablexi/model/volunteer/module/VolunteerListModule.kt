package com.mmy.charitablexi.model.volunteer.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       VolunteerListModule.kte.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class VolunteerListModule(val view:IView) {
   @Provides fun providerView() = view
}