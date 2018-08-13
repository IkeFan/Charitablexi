package com.mmy.charitablexi.model.project.component

import com.mmy.charitablexi.model.project.module.RaisingModule
import com.mmy.charitablexi.model.project.ui.fragment.RaisingFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.FragmentScope
import dagger.Component

/**
 * @file       RaisingComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@FragmentScope
@Component(modules = arrayOf(RaisingModule::class),dependencies = arrayOf(AppComponent::class))
interface RaisingComponent {
    fun inject(f:RaisingFragment)
}