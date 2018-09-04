package com.mmy.frame.data.bean

import java.io.Serializable

/**
 * @file       ProjectProgressBean.ktt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/22 0022
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */

//{
//    "name": "lucas",
//    "avatar": "/Uploads/2018-05-17/5afd243f05e69.jpg",
//    "id": "3",
//    "uid": "3",
//    "title": "每个孩子都接收不同的娱乐活动",
//    "uptime": "1526529805",
//    "photos": "/Uploads/2018-05-19/5aff886ae02a0.jpg,/Uploads/2018-05-19/5aff886ae8e0d.jpg"
//}

class ProjectProgressBean :IBean(){
    var data:List<ProjectProgress>? = null
    class ProjectProgress : Serializable{
        var name:String?= null
        var avatar:String? = null
        var id:Int?=null
        var uid:Int? = null
        var title:String? = null
        var uptime:Long?= null
        var photos:List<String>? = null
        var desc:String?= null
    }

}