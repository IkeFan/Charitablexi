package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.LetterModule
import com.mmy.charitablexi.model.personal.ui.fragment.LatterFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.FragmentScope
import dagger.Component

/**
 * @file       LetterComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@FragmentScope
@Component(modules = arrayOf(LetterModule::class), dependencies = arrayOf(AppComponent::class))
interface LetterComponent {
    fun inject(latterFragment: LatterFragment)
}