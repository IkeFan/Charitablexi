package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.TeamEditModule
import com.mmy.charitablexi.model.personal.ui.activity.TeamEditActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       TeamEditComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(TeamEditModule::class),dependencies = arrayOf(AppComponent::class))
interface TeamEditComponent {
    fun inject(a:TeamEditActivity)
}