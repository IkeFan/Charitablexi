package com.mmy.frame.data.bean

/**
 * @file       MessageEvent.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/2/28 0028
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class MessageEvent constructor(
        val type: Any? = null,//事件类型
        val data: Any? = null//传递的数据
)