package com.mmy.charitablexi.model.personal.module

import com.mmy.charitablexi.model.personal.view.SearchSupportView
import dagger.Module
import dagger.Provides

/**
 * @file       SearchSupportModule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/18 0018
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class SearchSupportModule(val view: SearchSupportView) {
    @Provides fun provideView() = view
}