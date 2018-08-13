package com.mmy.charitablexi.model.login.component

import com.mmy.charitablexi.model.login.module.LoginModule
import com.mmy.charitablexi.model.login.ui.activity.LoginActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       LoginComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(LoginModule::class),dependencies = arrayOf(AppComponent::class))
interface LoginComponent {
    fun inject(loginActivity: LoginActivity)
}