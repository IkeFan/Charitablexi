package com.mmy.frame.data.bean

/**
 * @file       OrgDetailBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/9/1 0001
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
class OrgDetailBean :IBean(){
    var data:Organization?= null

    class Organization{
        var id:Int=0
        var uid:Int=0
        var name:String?=null
        var logo:String?=null
        var desc:String?=null
        var email:String?=null
        var telphone:String?=null
        var nums:Int?=0
        var status: Int = 0
        var addtime:Int = 0
        var shtime: Int = 0
        var ygs: Int = 0
        var zps: Int = 0
        var zplist:List<Recruit>?=null
        var is_focus:Int?=0
    }

//    "id": "1",
//    "oid": "2",
//    "name": "远程教育走进农村课堂",
//    "photo": "/Uploads/2018-05-18/5afe6cf633d85.jpg",
//    "status": "1",
//    "endtime": "1526633801"

    class Recruit(var id:Int, var name:String,
                  var photo:String, var status:Int)
}