package com.mmy.frame.data.bean

/**
 * @file       CardetailBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/21 0021
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */

/*{
    "addtime": "1526711450",
    "content": "好评",
    "name": "lucas",
    "avatar": "/Uploads/2018-05-17/5afd243f05e69.jpg"
}*/
class CardetailBean :IBean(){
    var data: Detail? = null

    class Detail(var comments: List<comment>?=null)
    class comment(var addtime: String?=null, var content:String?=null
            ,var name:String?=null, var avatar: String?= null)
}