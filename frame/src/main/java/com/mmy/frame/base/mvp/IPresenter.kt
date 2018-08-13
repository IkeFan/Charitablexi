package com.mmy.frame.base.mvp

import android.app.Activity
import android.os.Handler
import com.mmy.frame.FrameApp
import com.mmy.frame.data.api.ApiService
import com.mmy.frame.helper.CommentHelper
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/23 0023 16:30
 * @描述          TODO
 */
open class IPresenter<V : IView> : CommentHelper {

    //注入View层
    @Inject lateinit var mV: V
    //注入model
    @Inject lateinit var mM: BaseModel
    @Inject lateinit var mApp: FrameApp
    @Inject lateinit var mHandler:Handler
    @Inject lateinit var mApi:ApiService

    fun finishActivity(){
        (mV as Activity).finish()
    }

}