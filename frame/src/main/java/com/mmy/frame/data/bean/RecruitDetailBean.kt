package com.mmy.frame.data.bean

/**
 * @file       RecruitDetailBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/9/4 0004
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class RecruitDetailBean {
    var data:RecruitDetail?=null
//{
//    "id": "6",
//    "oid": "2",
//    "name": "hehe",
//    "photo": "",
//    "persons": "21",
//    "address": "sz",
//    "worktime": "1535947200000,1535947200000",
//    "content": "adsgf",
//    "telphone": "13480104592",
//    "email": "asasf@qq.com",
//    "status": "1",
//    "addtime": "1535960569",
//    "endtime": "1",
//    "linkman": "机构名称1",
//    "ygs": "0",
//    "users": []
//}
    class RecruitDetail(
        var id:Int?=null, var oid:Int?=null, var name:String?=null,
        var photo:String?=null, var persons:Int?=0, var address:String?=null,
        var worktime:String?=null, var content:String? = null, var telphone:String?=null,
        var email:String? = null, var status:Int?=null, var addtime:Long?=0,
        var endtime:Long, var linkman:String? = null, var ygs:Int? = 0
    )
}