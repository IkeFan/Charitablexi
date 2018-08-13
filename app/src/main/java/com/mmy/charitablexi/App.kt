package com.mmy.charitablexi

import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import com.mmy.charitablexi.utils.MathUtil
import com.mmy.frame.FrameApp
import com.mmy.frame.utils.Config
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.rong.imkit.RongIM
import io.rong.imlib.RongIMClient
import java.util.*


/**
 * @file       App.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/12 0012
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class App : FrameApp() {

    companion object {
       lateinit var instance:App
    }

    override fun initApp() {
        instance = this
        FacebookSdk.sdkInitialize(this)
        val config = TwitterConfig.Builder(this)
                .logger(DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(TwitterAuthConfig("E4C55HT5IIAkcDCi7RgTk8rVV", "YHkuoDCJOepymfo0CFh8ZysyZakAAkFwXarnQBwmlmeleHwMjd"))
                .debug(true)
                .build()
        Twitter.initialize(config)
        AppEventsLogger.activateApp(this)
        return
    }

    fun initRong() {
        RongIM.init(this)
        val nonce = Random().nextInt(10000).toString() + ""
        val timestamp = (System.currentTimeMillis() / 1000).toString() + ""
        val signature = MathUtil.encryptToSHA(Config.RONG_SECRET + nonce + timestamp)
        val map = HashMap<String, String>()
        map.put("App-Key", Config.RONG_APPKEY)
        map.put("Nonce", nonce)
        map.put("Timestamp", timestamp)
        map.put("Signature", signature)
        if (userInfo.isLogin()) {
            mApi.getMsgUserToken(map, userInfo.id.toString(), userInfo.name ?: "未知", userInfo.headerImg ?: Config.DEF_USER_ICON)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        //获取token成功
                        userInfo.rongToken = it.token
                        connect(it.token)
                    },{

                    })
        }
    }

    private fun connect(token: String) {
        RongIM.connect(token, object : RongIMClient.ConnectCallback() {
            /**
             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
             * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
             */
            override fun onTokenIncorrect() {
                Log.e("token", "get token error2.")
            }

            /**
             * 连接融云成功
             * @param userid 当前 token 对应的用户 id
             */
            override fun onSuccess(userid: String) {
                Log.d("token", "--onSuccess" + userid)
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            override fun onError(errorCode: RongIMClient.ErrorCode) {
                Log.e("token", "get token error.")
            }
        })
    }
}