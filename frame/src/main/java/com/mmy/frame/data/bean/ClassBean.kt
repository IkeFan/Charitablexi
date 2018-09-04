package com.mmy.frame.data.bean

import java.io.Serializable

/**
 * @file       ClassBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/14 0014
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */

/*{
  "id":"2",
"uid": "3",
"title": "招工与求职",
"imgs": "/Uploads/2018-05-19/5affbb427dda3.jpg",
"content": "招工与求职的内容",
"is_free": "1",
"addtime": "1526709058"
}*/
class ClassBean :IBean(){
    var data: List<DataBean>?=null

    class DataBean :Serializable{
        var id:Int? = 0
        var uid:Int? =0
        var title:String? = null
        var imgs:List<String>?=null
        var content:String? = null
        var type:Int?=null
        var is_free:Int?=null
        var speaker:String?=null
        var addtime:String?=null
    }
}