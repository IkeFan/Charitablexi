package com.mmy.frame.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/19 0019 14:19
 * @描述 TODO
 */

public class ChoiceTypeBean extends IBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 4
         * name : 互联网
         */

        public String id;
        public String name;
    }
}
