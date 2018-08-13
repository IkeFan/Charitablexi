package com.mmy.frame.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/6/14 0014 10:09
 * @描述 TODO
 */

public class TeamInfoBean extends IBean implements Serializable{


    public List<DataBean> data;

    public static class DataBean implements Serializable{
        /**
         * id : 2
         * aid : 1
         * team_name : ANNI
         * team_img : /Uploads/2018-06-09/5b1b907ba5eb1.jpg
         * team_desc : HAMDE HALL COUNTRY DAY STUDENT
         */

        public String id;
        public String aid;
        public String team_name;
        public String team_img;
        public String team_desc;
    }
}
