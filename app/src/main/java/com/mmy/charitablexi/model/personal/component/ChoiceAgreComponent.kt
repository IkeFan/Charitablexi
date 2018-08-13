package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.ChoiceAgreModule
import com.mmy.charitablexi.model.personal.ui.activity.ChoiceAgreActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       ChoiceAgreComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/19 0019
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(ChoiceAgreModule::class),dependencies = arrayOf(AppComponent::class))
interface ChoiceAgreComponent {
    fun inject(ac:ChoiceAgreActivity)
}