package com.mmy.charitablexi.model.project.module

import com.mmy.charitablexi.model.project.view.ProjectInfoView
import dagger.Module
import dagger.Provides

/**
 * @file       ProjectInfoModuel.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/25 0025
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class ProjectInfoModuel(val view:ProjectInfoView) {
    @Provides fun provideView() = view
}