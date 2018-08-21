package com.mmy.charitablexi.model.commun.module

import com.mmy.frame.base.mvp.IView
import dagger.Module
import dagger.Provides

/**
 * @file       PublicClassModuleodule.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
@Module
class PublicClassModule(val iView: IView){
    @Provides fun providerView()=iView
}