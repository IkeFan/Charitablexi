package com.mmy.frame.data.bean

import com.google.gson.annotations.SerializedName


/**
 * @创建者     lucas
 * @创建时间   2017/12/28 0028 15:33
 * @描述          TODO
 */
data class BaseBean<D> constructor(
        @SerializedName("status") var status: Int = -1,
        @SerializedName("info") var msg: String,
        @SerializedName("data") var data:D
)