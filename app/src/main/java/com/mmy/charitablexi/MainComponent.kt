package com.mmy.charitablexi

import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       MainComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/12 0012
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = [(MainModule::class)],dependencies =arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}