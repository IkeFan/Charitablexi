package com.mmy.frame.utils

/**
 * @file       StringUtil.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/6/6 0006
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class StringUtil {
    companion object {
        fun formatModeil(modeil: String?): String {
            if (modeil.isNullOrEmpty())
                return ""
            val length = modeil!!.length
            if (length <= 4){
                return modeil
            }
            return "*******${modeil.substring(length-5,length-1)}"
        }
    }
}