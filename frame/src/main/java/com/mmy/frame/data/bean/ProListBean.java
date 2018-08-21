package com.mmy.frame.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/19 0019 18:20
 * @描述 TODO
 */

public class ProListBean extends IBean implements Serializable{

    public List<DataBean> data;

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * name : 公益项目1
         * img : /Uploads/2018-05-16/5afc291aa8dd9.jpg
         * addr : 西乡
         * title : 标题
         * description : 描述此公益项目
         * lovesum : 2000
         * yjrc : 7
         * yyax : 350
         * axjd : 0.175
         * ygs : 1
         */

        public String id;
        public String name;
        public String img;
        public String addr;
        public String title;
        public String description;
        public String lovesum;
        public String yjrc;
        public String yyax;
        public String axjd;
        public String ygs;
        public String xmlx;
    }
}
