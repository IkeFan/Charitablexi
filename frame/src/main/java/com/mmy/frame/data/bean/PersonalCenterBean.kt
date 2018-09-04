package com.mmy.frame.data.bean

import java.io.Serializable

/**
 * @file       PersonalCenterBean.ktkt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
/*{
    "name": "张三",
    "avatar": "/Uploads/2018-05-21/5b02632067c44.jpg",
    "id": null,
    "title": null,
    "content": null,
    "imgs": null
}*/
class PersonalCenterBean {
    var data :List<PersonalCenter>?=null
    class PersonalCenter :Serializable{
        var name:String? = null
        var avatar: String? = null
        var id: Int? = null
        var title:String? = null
        var content: String? = null
        var imgs:List<String>? = null
    }
}