package com.mmy.frame.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/18 0018 14:53
 * @描述 TODO
 */

public class SearchSupportBean extends IBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * name : wangLin
         */

        public int id;
        public String name;
    }
}
