package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.CommonProjectModule
import com.mmy.charitablexi.model.personal.ui.activity.CommonProjectActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       CommonProjectComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(CommonProjectModule::class),dependencies = arrayOf(AppComponent::class))
interface CommonProjectComponent {
    fun inject(ac:CommonProjectActivity)
}