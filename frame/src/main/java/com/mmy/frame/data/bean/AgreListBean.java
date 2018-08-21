package com.mmy.frame.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/19 0019 16:41
 * @描述 TODO
 */

public class AgreListBean extends IBean {

    public List<DataBean> data;

    public static class DataBean implements Serializable{
        /**
         * id : 2
         * title : 协议2
         * content : 协议2的内容
         * addtime : 1526471572
         */

        public String id;
        public String title;
        public String content;
        public String addtime;
    }
}
