package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.PersonalModule
import com.mmy.charitablexi.model.personal.ui.fragment.PersonalFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.FragmentScope
import dagger.Component

/**
 * @file       PersonalComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@FragmentScope
@Component(modules = arrayOf(PersonalModule::class),dependencies = arrayOf(AppComponent::class))
interface PersonalComponent {
    fun inject(ac:PersonalFragment)
}