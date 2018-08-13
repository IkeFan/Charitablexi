package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.QuestionEditModule
import com.mmy.charitablexi.model.personal.ui.activity.QuestionEditActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       QuestionEditComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/7 0007
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(QuestionEditModule::class),dependencies = arrayOf(AppComponent::class))
interface QuestionEditComponent {
    fun inject(ac:QuestionEditActivity)
}