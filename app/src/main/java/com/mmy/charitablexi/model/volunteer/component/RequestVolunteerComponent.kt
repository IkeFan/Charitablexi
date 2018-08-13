package com.mmy.charitablexi.model.volunteer.component

import com.mmy.charitablexi.model.volunteer.module.RequestVolunteerModule
import com.mmy.charitablexi.model.volunteer.ui.activity.RequestVolunteerActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       RequestVolunteerComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/26 0026
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(RequestVolunteerModule::class),dependencies = arrayOf(AppComponent::class))
interface RequestVolunteerComponent {
    fun inject(ac:RequestVolunteerActivity)
}