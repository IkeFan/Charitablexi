package com.mmy.frame.data.bean

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
class AddOrEdCardBean : IBean() {
    var data: List<AddOrEdCar>?=null
    class AddOrEdCar(
        var uid: Int?=null,
        var title: String?=null,
        var content: String?=null,
        var id: Int?=0,
        var imgs: String?=null,
        var type:Int?=null
    )
}