package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.AboutModule
import com.mmy.charitablexi.model.personal.ui.activity.AboutActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       AboutComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/11 0011
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(AboutModule::class),dependencies = arrayOf(AppComponent::class))
interface AboutComponent {
    fun inject(ac:AboutActivity)
}