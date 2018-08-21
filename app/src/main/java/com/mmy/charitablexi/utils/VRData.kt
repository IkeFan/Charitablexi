package com.mmy.charitablexi.utils

/**
 * @file       VRData.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/4/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
object VRData {
    fun getIntData(count:Int): ArrayList<Int> {
        val list = ArrayList<Int>()
        (0 .. count).forEach { list.add(it) }
        return list
    }
}