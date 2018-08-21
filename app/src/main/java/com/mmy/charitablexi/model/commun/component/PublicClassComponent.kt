package com.mmy.charitablexi.model.commun.component

import com.mmy.charitablexi.model.commun.module.PublicClassModule
import com.mmy.charitablexi.model.commun.ui.fragment.OpenClassFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.FragmentScope
import dagger.Component

/**
 * @file       PublicClassComponent.ktnt.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@FragmentScope
@Component(modules = arrayOf(PublicClassModule::class), dependencies = arrayOf(AppComponent::class))
interface PublicClassComponent {
    fun inject(openClassFragment: OpenClassFragment)
}
