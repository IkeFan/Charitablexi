package com.mmy.frame

import android.app.Application
import android.os.Handler
import com.blankj.utilcode.util.Utils
import com.mmy.frame.data.api.ApiService
import com.mmy.frame.data.api.module.ApiServiceModule
import com.mmy.frame.data.bean.UserInfo
import com.squareup.otto.Bus
import okhttp3.Cache
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/23 0023 16:30
 * @描述          程序入口
 */
abstract class FrameApp : Application() {

    @Inject lateinit var handler: Handler
    @Inject lateinit var mFrameApp: FrameApp
    @Inject lateinit var cache: Cache
    @Inject lateinit var mBus: Bus
    @Inject lateinit var mApi: ApiService

    var appComponent: AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .apiServiceModule(ApiServiceModule())
            .build()

    var userInfo: UserInfo = UserInfo()

    companion object {
       lateinit var frameInstance: FrameApp
    }

    override fun onCreate() {
        super.onCreate()
        frameInstance = this
        Utils.init(this)
        appComponent.inject(this)
        initApp()
    }

    abstract fun initApp()


}