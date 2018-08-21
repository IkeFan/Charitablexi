package com.mmy.frame.data.bean

/**
 * @file       OrganizationBean.kt
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
    "uid": "3",
    "name": "机构名称1",
    "logo": "/Uploads/2018-05-17/5afd243f05e69.jpg",
    "desc": "介绍机构",
    "email": "666@qq.com",
    "telphone": "15922224444",
    "nums": "0",
    "status": "2",
    "addtime": "1526539327",
    "shtime": "0",
    "ygs": "3",
    "zps": "1"
}*/
class OrganizationBean :IBean(){
   var  data:List<Organization>?=null

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
    }
}