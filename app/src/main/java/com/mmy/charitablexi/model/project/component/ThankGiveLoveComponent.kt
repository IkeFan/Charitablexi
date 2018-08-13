package com.mmy.charitablexi.model.project.component

import com.mmy.charitablexi.model.project.module.ThankGiveLoveModule
import com.mmy.charitablexi.model.project.ui.activity.ThankGiveLoveActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       ThankGiveLoveComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(ThankGiveLoveModule::class),dependencies = arrayOf(AppComponent::class))
interface ThankGiveLoveComponent {
    fun inject(ac:ThankGiveLoveActivity)
}