package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.AboutEditModule
import com.mmy.charitablexi.model.personal.ui.activity.AboutEditActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       AboutEditComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(AboutEditModule::class),dependencies = arrayOf(AppComponent::class))
interface AboutEditComponent {
    fun inject(a:AboutEditActivity)
}