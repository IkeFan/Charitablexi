package com.mmy.frame.base.mvp

import com.blankj.utilcode.util.NetworkUtils
import com.mmy.frame.FrameApp
import com.mmy.frame.helper.CommentHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @file       BaseModelkt
 * @brief      网络请求解析
 * @author     lucas
 * @date       2018/4/12 0012
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
open class BaseModel @Inject constructor() : CommentHelper {

    private val TAG = "net"

    @Inject lateinit var mFrameApp: FrameApp

    //请求体
    var call: Observable<*>? = null

    //回调
    var _success: (dataBean: Any) -> Unit = {}
    var _fail: (e: Throwable) -> Unit = {}


    fun request(init: BaseModel.() -> Unit) {
        //初始化
        init()
        //判断网络是否可用
        if (!NetworkUtils.isConnected()) {
            "网络不可用".ld(TAG)
            "网络不可用".showToast(mFrameApp)
            _fail(RuntimeException("网络不可用"))
        }

        //请求数据
        call?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    _success(it)
                }, {
                    _fail(it)
                }, {})
    }
}