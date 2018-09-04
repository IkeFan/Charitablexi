package com.mmy.frame.utils

import com.mmy.frame.BuildConfig


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:22
 * @描述          TODO
 */
object Config {
    //微信appkey
    const val WEIXIN_APPKEY = BuildConfig.WEIXIN_APPKEY
    const val WECHAT_APP_SECRET = BuildConfig.WECHAT_APP_SECRET

    //融云
    val RONG_APPKEY = BuildConfig.RONG_APPKEY
    val RONG_SECRET = BuildConfig.RONG_SECRET

    //本地缓存大小
    val CACHE_SIZE: Long = 1024 * 1024 * 10

    //连接超时时间
    val CONN_TIME_OUT: Long = 15 * 1000

    //数据读取超时时间
    val READ_TIME_OUT: Long = 15 * 1000

    //splash_maimaiyun 时间
    val SPLASH_DISPLAY_TIME = 2000

    const val HOST = "http://www.charitytown2018.com"

    //服务器地址
    val BASE_URL = "$HOST/Api/"

    //版本更新
    const val VERSION = "version/latest"

    //默认用户头像
    const val DEF_USER_ICON = "http://sr.photos3.fotosearch.com/bthumb/CSP/CSP362/k44707495.jpg"

    //天气查询
    const val SEARCH_WEATER = "http://weatherapi.market.xiaomi.com/wtr-v2/weather"

    //即时通讯获取用户token
    const val MSG_USER_TOKEN = "http://api.cn.ronghub.com/user/getToken.json"
    //融云聊天室禁言
    const val AN_EXCUSE = "http://api.cn.ronghub.com/chatroom/user/gag/add.json"

    //注册
    const val REGISTER = "Login/register"

    //登录
    const val LOGIN = "Login/login"

    //发送验证码
    const val SEND_CODE = "Login/sendSms"

    //发送验证码
    const val CHECK_CODE = "Check/smsCode"

    //获取微信的token openid
    const val GET_TOKEN_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WEIXIN_APPKEY +
            "&secret=" + WECHAT_APP_SECRET + "&grant_type=authorization_code"

    //获取微信用户个人信息
    const val WX_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?"
}