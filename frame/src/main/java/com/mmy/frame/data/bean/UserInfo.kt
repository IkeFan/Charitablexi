package com.mmy.frame.data.bean

import android.util.Log
import com.mmy.frame.FrameApp
import java.util.*

/**
 * @file       UserInfo.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/5/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class UserInfo {
    var id: Int? = null
        get() = if (field == null) 0 else field
    var token: String? = null
    var name: String? = null
    var type: Int? = 0
    var headerImg: String? = null
    var mobile: String? = null
    var email: String? = null
    var lovesum: Int = 0

    var userBean:PersonalInfoBean.DataBean?=null

    //融云token
    var rongToken: String? = null

    fun getIdCheckLogin(): Int {
        if (id == 0 || id == null) {
            FrameApp.frameInstance.mBus.post(UserEvent("login"))
            return 0
        } else
            return id!!
    }


    //用户身份
    var userLevel: Int = 0
        get() {
            val int = Random().nextInt(3)
            Log.d("lucas", int.toString())
            return int
        }

    fun isLogin() = id != null

    companion object {

        //根据类型值获取类型名称
        fun getUserTypeName(type: Int): String {
            when (type) {
                0 -> {
                    return UserType.NORMAL_USER.typeName
                }
                1 -> {
                    return UserType.MECHANISM_USER.typeName
                }
                else -> {
                    return UserType.PLATFORM_USER.typeName
                }
            }
        }
    }

    //用户身份类型
    enum class UserType(val type: Int, val typeName: String) {
        NORMAL_USER(0, "普通用户"),
        MECHANISM_USER(1, "机构方"),
        PLATFORM_USER(2, "平台方")
    }

    open class UserEvent(val action: String)
}