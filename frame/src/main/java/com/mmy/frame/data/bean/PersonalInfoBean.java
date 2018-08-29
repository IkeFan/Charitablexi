package com.mmy.frame.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2018/5/28 0028 15:27
 * @描述 TODO
 */

public class PersonalInfoBean extends IBean {

    /**
     * data : {"id":"5","name":"张三","avatar":"/Uploads/2018-05-21/5b02632067c44.jpg","type":"0","lovesum":"2000",
     * "guanzhu":"2","gzz":"1","zhuzhi":"0","xiaoxi":"6","fqxm":"0","scxm":"0","yjxm":null,"ygxm":"1"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * id : 5
         * name : 张三
         * avatar : /Uploads/2018-05-21/5b02632067c44.jpg
         * type : 0  //0-通会员 1-管理者
         * lovesum : 2000  //爱心值
         * guanzhu : 2  //关注
         * gzz : 1    //关注者
         * zhuzhi : 0   //参与组织数
         * xiaoxi : 6  //消息数
         * fqxm : 0   //发起项目数
         * scxm : 0   //收藏项目数
         * yjxm : null  //已捐项目数
         * xy_counts: 0 //协议管理数
         * ygxm : 1    //参加义工项目数
         * users_counts:0 用户总数
         */

        public int id;
        public String name;
        public String avatar;
        public int type;
        public String lovesum;
        public String guanzhu;
        public String gzz;
        public String zhuzhi;
        public String xiaoxi;
        public String fqxm;
        public String scxm;
        public String yjxm;
        public String ygxm;
        public int xy_counts;
        public int users_counts;
    }
}