package com.mmy.frame.data.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/6/11 0011 15:43
 * @描述 TODO
 */

public class AboutBean extends IBean implements Serializable{

    /**
     * mData : {"id":"1","name":"慈善喜","img":"/Uploads/2018-06-09/5b1b898c32187.jpg","desc":"Charity Easy
     * Introduction\nCharity Easy is a mobile public benefit social networking platform.\nOn this platform，users,
     * non-profit organizations, and enterprises can recruit volunteers, initiate, execute, and donate various kinds
     * of public benefit activities. Ordinary users donate &quot;love&quot;, little hearts that count as credits,
     * through healthy living styles and supporting public benefit projects. \nThis platform can help donors,
     * volunteer organizations, and the general public to collaborate together to achieve a win-win situation for
     * individuals, companies, public organizations, and the charities beneficiaries. \nThis app aims to increase
     * public participation, convenience, and transparency of charity welfare in Connecticut and other regions.",
     * "url":"www.mozi.com","teams":[{"id":"2","team_name":"ANNI","team_img":"/Uploads/2018-06-09/5b1b907ba5eb1.jpg",
     * "team_desc":"HAMDE HALL COUNTRY DAY STUDENT"},{"id":"1","team_name":"ANNI2",
     * "team_img":"/Uploads/2018-06-09/5b1b8b4fa5963.jpg","team_desc":"HAMDE HALL COUNTRY DAY STUDENT"}],
     * "linkmans":[{"id":"2","link_name":"TANNA2","link_img":"/Uploads/2018-06-09/5b1b906f80c03.jpg",
     * "link_tel":"13566667777","link_email":"2342342@qq.com","link_wechat":"2342342"},{"id":"1",
     * "link_name":"TANNAa","link_img":"/Uploads/2018-06-09/5b1b905febf7d.jpg","link_tel":"13566667777",
     * "link_email":"2342342@qq.com","link_wechat":"2342342"}]}
     */

    public DataBean data;

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * name : 慈善喜
         * img : /Uploads/2018-06-09/5b1b898c32187.jpg
         * desc : Charity Easy Introduction
         Charity Easy is a mobile public benefit social networking platform.
         On this platform，users, non-profit organizations, and enterprises can recruit volunteers, initiate, execute,
         and donate various kinds of public benefit activities. Ordinary users donate &quot;love&quot;, little hearts
         that count as credits, through healthy living styles and supporting public benefit projects.
         This platform can help donors, volunteer organizations, and the general public to collaborate together to
         achieve a win-win situation for individuals, companies, public organizations, and the charities beneficiaries.
         This app aims to increase public participation, convenience, and transparency of charity welfare in
         Connecticut and other regions.
         * url : www.mozi.com
         * teams : [{"id":"2","team_name":"ANNI","team_img":"/Uploads/2018-06-09/5b1b907ba5eb1.jpg",
         * "team_desc":"HAMDE HALL COUNTRY DAY STUDENT"},{"id":"1","team_name":"ANNI2",
         * "team_img":"/Uploads/2018-06-09/5b1b8b4fa5963.jpg","team_desc":"HAMDE HALL COUNTRY DAY STUDENT"}]
         * linkmans : [{"id":"2","link_name":"TANNA2","link_img":"/Uploads/2018-06-09/5b1b906f80c03.jpg",
         * "link_tel":"13566667777","link_email":"2342342@qq.com","link_wechat":"2342342"},{"id":"1",
         * "link_name":"TANNAa","link_img":"/Uploads/2018-06-09/5b1b905febf7d.jpg","link_tel":"13566667777",
         * "link_email":"2342342@qq.com","link_wechat":"2342342"}]
         */

        public String id;
        public String             name;
        public String             img;
        public String             desc;
        public String             url;
        public List<TeamsBean>    teams;
        public List<LinkmansBean> linkmans;

        public static class TeamsBean implements Serializable{
            /**
             * id : 2
             * team_name : ANNI
             * team_img : /Uploads/2018-06-09/5b1b907ba5eb1.jpg
             * team_desc : HAMDE HALL COUNTRY DAY STUDENT
             */

            public String id;
            public String team_name;
            public String team_img;
            public String team_desc;
        }

        public static class LinkmansBean implements Serializable{
            /**
             * id : 2
             * link_name : TANNA2
             * link_img : /Uploads/2018-06-09/5b1b906f80c03.jpg
             * link_tel : 13566667777
             * link_email : 2342342@qq.com
             * link_wechat : 2342342
             */

            public String id;
            public String link_name;
            public String link_img;
            public String link_tel;
            public String link_email;
            public String link_wechat;
        }
    }
}
