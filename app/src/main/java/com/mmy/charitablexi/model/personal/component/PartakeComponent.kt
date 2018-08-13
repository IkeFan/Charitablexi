package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.PartakeModule
import com.mmy.charitablexi.model.personal.ui.activity.PartakeActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       PartakeComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/29 0029
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(PartakeModule::class),dependencies = arrayOf(AppComponent::class))
interface PartakeComponent {
    fun inject(ac:PartakeActivity)
}