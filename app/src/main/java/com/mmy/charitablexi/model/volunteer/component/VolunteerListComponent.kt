package com.mmy.charitablexi.model.volunteer.component

import com.mmy.charitablexi.model.project.ui.activity.VolunteerListActivity
import com.mmy.charitablexi.model.volunteer.module.VolunteerListModule
import com.mmy.charitablexi.model.volunteer.ui.fragment.VolunteerFragment
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       VolunteerListComponent.ktt.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/15 0015
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(VolunteerListModule::class), dependencies = arrayOf(AppComponent::class))
interface VolunteerListComponent {
    fun inject(volunteerFragment: VolunteerFragment)
    fun inject(volunteerListActivity: VolunteerListActivity)
}