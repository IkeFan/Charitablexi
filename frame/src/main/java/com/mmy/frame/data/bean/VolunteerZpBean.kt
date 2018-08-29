package com.mmy.frame.data.bean

/**
 * @file       VolunteerOrgnBeanBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
/*{
    "id": null,
    "name": null,
    "logo": null,
    "ygs": "0"
}*/
class VolunteerZpBean :IBean(){
    var data: List<Orgonization>?=null
    class Orgonization(var id:Int?=null, var name:String?=null,
                       var logo:String?=null, var ygs:String?=null)
}