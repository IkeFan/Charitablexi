package com.mmy.charitablexi.model.commun.component

import com.mmy.charitablexi.model.commun.module.InteractModule
import com.mmy.charitablexi.model.commun.ui.fragment.InteractionFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.FragmentScope
import dagger.Component

/**
 * @file       InteractComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@FragmentScope
@Component(modules = [(InteractModule::class)], dependencies = [(AppComponent::class)])
interface InteractComponent {
    fun inject(interactionFragment: InteractionFragment)
}