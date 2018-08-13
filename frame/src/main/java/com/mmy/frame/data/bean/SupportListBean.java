package com.mmy.frame.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/18 0018 17:54
 * @描述 TODO
 */

public class SupportListBean extends IBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * name : wangLin
         * is_zhf : 1
         * material : 捐赠1000美元
         */

        public int id;
        public String name;
        public String is_zhf;
        public String material;
        public int position;
    }
}
