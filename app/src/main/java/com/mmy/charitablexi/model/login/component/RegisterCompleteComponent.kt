package com.mmy.charitablexi.model.login.component

import com.mmy.charitablexi.model.login.module.RegisterCompleteModule
import com.mmy.charitablexi.model.login.ui.activity.RegisterCompleteActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       RegisterCompleteComponentComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(RegisterCompleteModule::class),dependencies = arrayOf(AppComponent::class))
interface RegisterCompleteComponent {
    fun inject(registerCompleteActivity: RegisterCompleteActivity)
}