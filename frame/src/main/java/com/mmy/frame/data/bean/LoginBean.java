package com.mmy.frame.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2018/4/25 0025 11:37
 * @描述 TODO
 */

public class LoginBean extends IBean {

    /**
     * data : {"id":"3","name":"lucas","type":"0","mobile":"18825204205",
     * "password":"6854cc645509cc4cbffc5e6ffcaf96a2","email":"123@qq.com","sex":"2","age":"35","avatar":null,
     * "salt":"5ae02bf5d9c57","invite_code":"5xb1r","token":"4a383a9c390f457935b2dda35896e9f8","is_zhf":"0",
     * "material":null,"login_time":"1524640757","create_time":"1524640757","is_audit":"0"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * id : 3
         * name : lucas
         * type : 0
         * mobile : 18825204205
         * password : 6854cc645509cc4cbffc5e6ffcaf96a2
         * email : 123@qq.com
         * sex : 2
         * age : 35
         * avatar : null
         * salt : 5ae02bf5d9c57
         * invite_code : 5xb1r
         * token : 4a383a9c390f457935b2dda35896e9f8
         * is_zhf : 0
         * material : null
         * login_time : 1524640757
         * create_time : 1524640757
         * is_audit : 0
         */

        public String id;
        public String name;
        public String type;
        public String mobile;
        public String password;
        public String email;
        public String sex;
        public String age;
        public Object avatar;
        public String salt;
        public String invite_code;
        public String token;
        public String is_zhf;
        public Object material;
        public String login_time;
        public String create_time;
        public String is_audit;
        public int lovesum;
    }
}
