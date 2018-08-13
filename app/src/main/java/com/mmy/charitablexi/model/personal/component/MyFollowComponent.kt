package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.MyFollowModule
import com.mmy.charitablexi.model.personal.ui.activity.MyFollowActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       MyFollowComponent * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = arrayOf(MyFollowModule::class),dependencies = arrayOf(AppComponent::class))
interface MyFollowComponent {
    fun inejct(ac: MyFollowActivity)
}