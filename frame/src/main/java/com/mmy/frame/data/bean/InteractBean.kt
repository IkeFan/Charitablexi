package com.mmy.frame.data.bean

import java.io.Serializable

/**
 * @file       InteractBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/16 0016
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
/*{
    "name": "wangLin",
    "avatar": "/Uploads/2018-05-17/5afd701ade84e.jpg",
    "id": "4",
    "title": "招工与求职111",
    "imgs": "/Uploads/2018-08-20/5b7a1631632ea.png",
    "type": "1",
    "cardsCounts": "0"
}*/
class InteractBean: IBean() {
    var data: List<Interact>?=null
    class Interact :Serializable{
        var name: String? = null
        var avatar: String?=null
        var id: Int?=0
        var title: String?=null
        var imgs: List<String>?=null
        var type:Int?=null
        var cardsCounts: Int?=0
    }
}