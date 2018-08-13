package com.mmy.frame.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/28 0028 18:35
 * @描述 TODO
 */

public class FollowListBean extends IBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * oid : null
         * uid : 5
         * bid : 1
         * addtime : 1526725150
         * name : 张三
         * avatar : /Uploads/2018-05-21/5b02632067c44.jpg
         */

        public String oid;
        public String uid;
        public String bid;
        public String addtime;
        public String name;
        public String avatar;
    }
}
