package com.mmy.frame.data.bean;

import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/28 0028 16:56
 * @描述 TODO
 */

public class CommonProjectBean extends IBean {

    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 2
         * name : 公益项目1
         * img : /Uploads/2018-05-19/5aff93685e945.jpg
         * addr : 深圳
         * title :
         * description : 简单描述此公益项目
         */

        public String id;
        public String name;
        public String img;
        public String addr;
        public String title;
        public String description;
        public String status;
    }
}
