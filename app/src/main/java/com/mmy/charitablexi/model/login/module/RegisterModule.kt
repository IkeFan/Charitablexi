package com.mmy.charitablexi.model.login.module

import com.mmy.charitablexi.model.login.view.RegisterView
import dagger.Module
import dagger.Provides

/**
 * @file       RegisterModule.ktt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class RegisterModule(val registerView: RegisterView) {
    @Provides fun provideView() = registerView
}