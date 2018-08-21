package com.mmy.charitablexi.model.personal.component

import com.mmy.charitablexi.model.personal.module.AgreedManagerModule
import com.mmy.charitablexi.model.personal.ui.activity.AddAgreActivity
import com.mmy.charitablexi.model.personal.ui.activity.AgreementManagerActivity
import com.mmy.frame.AppComponent
import com.mmy.frame.base.annotation.ActivityScope
import dagger.Component

/**
 * @file       AgreedManagerComponentent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@ActivityScope
@Component(modules = [AgreedManagerModule::class], dependencies = [AppComponent::class])
interface AgreedManagerComponent {
    fun inject(agreementManagerActivity: AgreementManagerActivity)
    fun inject(addAgreActivity: AddAgreActivity)
}