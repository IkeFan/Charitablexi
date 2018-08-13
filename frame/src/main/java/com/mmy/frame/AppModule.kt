package com.mmy.frame

import android.os.Handler
import com.google.gson.Gson
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @创建者     lucas
 * @创建时间   2017/12/26 0026 10:58
 * @描述          组件
 */
@Module
class AppModule(private val frameApp: FrameApp) {

    @Provides
    fun provideApp() = frameApp

    @Provides
    @Singleton
    fun provideHandler() = Handler()

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideBus() = Bus()
}