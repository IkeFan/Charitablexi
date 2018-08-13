package com.mmy.frame

import android.os.Handler
import com.google.gson.Gson
import com.mmy.frame.data.api.ApiService
import com.mmy.frame.data.api.module.ApiServiceModule
import com.squareup.otto.Bus
import dagger.Component
import javax.inject.Singleton


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:12
 * @描述          TODO
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiServiceModule::class
))
interface AppComponent {
    fun getApp(): FrameApp
    fun getHandler():Handler
    fun getApiService(): ApiService
    fun getGson():Gson
    fun getBus():Bus
    fun inject(frameApp: FrameApp)
}