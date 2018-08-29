package com.mmy.frame.data.bean

/**
 * @file       SponsorDetailBean.kt
 * @brief      描述
 * @author     lucas
 * @date       2018/8/22 0022
 * @version    V1.0
 * @par        Copyright (c):
 * @par History:
 *             version: zsr, 2017-09-23
 */
/*{
    "id": "1",
    "name": "wangLin",
    "avatar": "/Uploads/2018-05-17/5afd701ade84e.jpg",
    "counts": "0",
    "lists": []
}*/
class SponsorDetailBean :IBean(){
    var data:SponsortDetail?=null
    class SponsortDetail(var id:Int, var name:String,
                        var avatar:String, var counts:Int,
                        var lists:List<SupportPro>)
    class  SupportPro(var id:Int, var name:String, var img: String, var description:String,var addTime:Long)
}