package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.ModifyInfoModule
import com.mmy.charitablexi.model.personal.ui.activity.ModifyInfoActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       ModifyInfoComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/6 0006
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(ModifyInfoModule::class),dependencies = arrayOf(AppComponent::class))
interface ModifyInfoComponent {
    fun inject(ac:ModifyInfoActivity)
}