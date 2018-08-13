package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.AboutGeneraManagerModule
import com.mmy.charitablexi.model.personal.ui.activity.AboutGeneraManagerActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       AboutGeneraManagerComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(AboutGeneraManagerModule::class),dependencies = arrayOf(AppComponent::class))
interface AboutGeneraManagerComponent {
    fun inject(a:AboutGeneraManagerActivity)
}