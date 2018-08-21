package com.mmy.charitablexi.model.personal.module

import com.mmy.charitablexi.model.personal.view.ProjectListView
import dagger.Module
import dagger.Provides

/**
 * @file       PublishProjectModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class PublishProjectModule(val iView: ProjectListView) {
    @Provides fun provideView() = iView
}