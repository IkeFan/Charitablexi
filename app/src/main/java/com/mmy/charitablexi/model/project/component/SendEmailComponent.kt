package com.mmy.charitablexi.model.project.component

import com.mmy.charitablexi.model.project.module.SendEmailModule
import com.mmy.charitablexi.model.project.ui.activity.SendEmailActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       SendEmailComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(SendEmailModule::class),dependencies = arrayOf(AppComponent::class))
interface SendEmailComponent {
    fun inject(ac:SendEmailActivity)
}