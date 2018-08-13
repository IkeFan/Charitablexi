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
         * type : 0
         * lovesum : 2000
         * guanzhu : 2
         * gzz : 1
         * zhuzhi : 0
         * xiaoxi : 6
         * fqxm : 0
         * scxm : 0
         * yjxm : null
         * ygxm : 1
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
    }
}
