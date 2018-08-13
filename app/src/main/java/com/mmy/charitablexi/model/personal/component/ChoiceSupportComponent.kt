package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.ChoiceSupportModule
import com.mmy.charitablexi.model.personal.ui.activity.ChoiceSupportActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       ChoiceSupportComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(ChoiceSupportModule::class),dependencies = arrayOf(AppComponent::class))
interface ChoiceSupportComponent {
    fun inject(ac:ChoiceSupportActivity)
}