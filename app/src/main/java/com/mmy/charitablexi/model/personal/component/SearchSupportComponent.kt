package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.SearchSupportModule
import com.mmy.charitablexi.model.personal.ui.activity.SearchSupportActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       SearchSupportComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(SearchSupportModule::class),dependencies = arrayOf(AppComponent::class))
interface SearchSupportComponent {
    fun inject(activity:SearchSupportActivity)
}