package com.mmy.frame.data.bean

import com.google.gson.annotations.SerializedName

data class VersionBean(@SerializedName("version_code")
                       val versionCode: Int = -1)