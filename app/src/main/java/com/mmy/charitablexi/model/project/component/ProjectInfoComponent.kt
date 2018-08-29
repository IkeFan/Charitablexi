package com.mmy.charitablexi.model.project.component

import com.mmy.charitablexi.model.project.module.ProjectInfoModuel
import com.mmy.charitablexi.model.project.ui.activity.CommonActivity
import com.mmy.charitablexi.model.project.ui.activity.ProjectInfoActivity
import com.mmy.charitablexi.model.project.ui.activity.SendLoveActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       ProjectInfoComponent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(ProjectInfoModuel::class),dependencies = arrayOf(AppComponent::class))
interface ProjectInfoComponent {
    fun inject(ac:ProjectInfoActivity)
    fun inject(commonActivity: CommonActivity)
    fun inject(sendLoveActivity: SendLoveActivity)
}