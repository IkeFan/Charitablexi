package com.mmy.charitablexi.base

import com.mmy.charitablexi.model.commun.ui.activity.PublicCardActivity
import com.mmy.charitablexi.model.commun.ui.activity.PublishArticleActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       BaseComponent.kt
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
interface BaseComponent {
    fun inject( publicCardActivity: PublicCardActivity)
    fun inject( publishArticleActivity: PublishArticleActivity)
}