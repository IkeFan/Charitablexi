package com.mmy.frame.data.api.module

import com.google.gson.Gson
import com.mmy.frame.FrameApp
import com.mmy.frame.data.api.ApiService
import com.mmy.frame.interceptor.ParamsInterceptor
import com.mmy.frame.utils.Config
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:18
 * @描述          网络层
 */
@Module
class ApiServiceModule @Inject constructor() {
    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache,paramsInterceptor: ParamsInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(paramsInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .readTimeout(Config.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(Config.CONN_TIME_OUT, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .build()

    @Singleton
    @Provides
    fun provideCache(frameApp: FrameApp):Cache =
            Cache(frameApp.cacheDir, Config.CACHE_SIZE)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gson: Gson):Retrofit =
            Retrofit.Builder()
                    .baseUrl(Config.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)
}