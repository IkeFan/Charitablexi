package com.mmy.frame.data.bean;

/**
 * @创建者 lucas
 * @创建时间 2018/4/25 0025 10:43
 * @描述 TODO
 */

public class RegisterBean extends IBean {

    /**
     * data : {"id":"2","name":"11","type":"0","mobile":"18825204201","password":"83a5dbb2792903d5f951857153271e30",
     * "salt":"5adfe9c3103f5","invite_code":"5xb1r","login_time":"1524623811","create_time":"1524623811","is_audit":"0"}
     */

    public DataBean data;

    public static class DataBean {
        /**
         * id : 2
         * name : 11
         * type : 0
         * mobile : 18825204201
         * password : 83a5dbb2792903d5f951857153271e30
         * salt : 5adfe9c3103f5
         * invite_code : 5xb1r
         * login_time : 1524623811
         * create_time : 1524623811
         * is_audit : 0
         */

        public String id;
        public String name;
        public String type;
        public String mobile;
        public String password;
        public String salt;
        public String invite_code;
        public String login_time;
        public String create_time;
        public String is_audit;
    }
}
