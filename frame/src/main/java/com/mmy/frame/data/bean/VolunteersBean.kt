package com.mmy.frame.data.bean

/**
 * @file       VolunteersBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */

/*{
    "id": "2",
    "uid": "5",
    "name": "张三",
    "mobile": "15071417331",
    "sex": "1",
    "age": "28",
    "email": "123456@qq.com"
}*/
class VolunteersBean :IBean(){
    var data: List<Volunteer>?=null

    class Volunteer{
        var id:Int?=0
        var uid: Int?=0
        var name: String?=null
        var mobile: String?=null
        var sex: Int = 0
        var age: Int = 0
        var email: String?=null
    }
}