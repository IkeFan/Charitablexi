package com.mmy.charitablexi.model.commun.component

import com.mmy.charitablexi.base.BaseIViewModule
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       AddOrEditCardComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/20 0020
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = [BaseIViewModule::class], dependencies = [AppComponent::class])
interface AddOrEditCardComponent {
//    fun inject(publicCardActivity: PublicCardActivity)
}