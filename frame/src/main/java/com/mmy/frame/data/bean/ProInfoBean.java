package com.mmy.frame.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @创建者 lucas
 * @创建时间 2018/5/26 0026 9:51
 * @描述 TODO
 */

public class ProInfoBean extends IBean implements Serializable {

    /**
     * data : {"id":"1","uid":"1","zxid":"3","zid":",1,2,3,","importid":"3","typeid":"2","name":"公益项目1",
     * "img":"/Uploads/2018-05-16/5afc291aa8dd9.jpg","addr":"西乡","title":"标题","lovesum":"2000","volunteers":"100",
     * "description":"描述此公益项目","xyid":"1","advsid":"2","status":"3","addtime":"1526475034","endtime":"1526474390",
     * "nums":"8","yjlove":"850","nowyg":"1","users":[{"id":"5","avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"}],
     * "zhfList":[{"id":"1","name":"wangLin","avatar":"/Uploads/2018-05-17/5afd701ade84e.jpg","material":"xxx"},
     * {"id":"2","name":"wangLin","avatar":"/Uploads/2018-05-17/5afd2287126b2.jpg","material":"ggg"},{"id":"3",
     * "name":"lucas","avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg","material":"jjhggdeee"}],"adinfo":{"id":"2",
     * "title":"广告2","imgs":"/Uploads/2018-05-16/5afc20d69dd35.jpg","content":"广告2的内容","addtime":"1526472918"},
     * "comments":[{"addtime":"1526871867","content":"用户5回复用户1","name":"张三",
     * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526871816","content":"用户5回复用户3","name":"张三",
     * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526711450","content":"好评","name":"lucas",
     * "avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg"},{"addtime":"1526710450","content":"用户5评论帖子1","name":"张三",
     * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526710275","content":"用户3评论帖子1","name":"lucas",
     * "avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg"},{"addtime":"1526644073","content":"好评好评","name":"lucas",
     * "avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg"},{"addtime":"1526644026","content":"差评差评","name":"张三",
     * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526643801","content":"差评差评","name":"张三",
     * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"}],"discusCounts":"3"}
     */

    public DataBean data;

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * uid : 1
         * zxid : 3
         * zid : ,1,2,3,
         * importid : 3
         * typeid : 2
         * name : 公益项目1
         * img : /Uploads/2018-05-16/5afc291aa8dd9.jpg
         * addr : 西乡
         * title : 标题
         * lovesum : 2000
         * volunteers : 100
         * description : 描述此公益项目
         * xyid : 1
         * advsid : 2
         * status : 3
         * addtime : 1526475034
         * endtime : 1526474390
         * nums : 8
         * yjlove : 850
         * nowyg : 1
         * users : [{"id":"5","avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"}]
         * zhfList : [{"id":"1","name":"wangLin","avatar":"/Uploads/2018-05-17/5afd701ade84e.jpg","material":"xxx"},
         * {"id":"2","name":"wangLin","avatar":"/Uploads/2018-05-17/5afd2287126b2.jpg","material":"ggg"},{"id":"3",
         * "name":"lucas","avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg","material":"jjhggdeee"}]
         * adinfo : {"id":"2","title":"广告2","imgs":"/Uploads/2018-05-16/5afc20d69dd35.jpg","content":"广告2的内容",
         * "addtime":"1526472918"}
         * comments : [{"addtime":"1526871867","content":"用户5回复用户1","name":"张三",
         * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526871816","content":"用户5回复用户3",
         * "name":"张三","avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526711450","content":"好评",
         * "name":"lucas","avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg"},{"addtime":"1526710450",
         * "content":"用户5评论帖子1","name":"张三","avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},
         * {"addtime":"1526710275","content":"用户3评论帖子1","name":"lucas",
         * "avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg"},{"addtime":"1526644073","content":"好评好评","name":"lucas",
         * "avatar":"/Uploads/2018-05-17/5afd243f05e69.jpg"},{"addtime":"1526644026","content":"差评差评","name":"张三",
         * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"},{"addtime":"1526643801","content":"差评差评","name":"张三",
         * "avatar":"/Uploads/2018-05-21/5b02632067c44.jpg"}]
         * discusCounts : 3
         */

        public String     id;
        public String     uid;
        public String     zxid;
        public String     zid;
        public String     importid;
        public String     typeid;
        public String     name;
        public String     img;
        public String     addr;
        public String     title;
        public int        lovesum;
        public String     volunteers;
        public String     description;
        public String     xyid;
        public String     advsid;
        @SerializedName("status")
        public String     statusX;
        public String     addtime;
        public String     endtime;
        public int        nums;
        public int        yjlove;
        public String     nowyg;
        public AdinfoBean adinfo;
        public String     discusCounts;
        public ZxfInfo    zxfinfo;
        public List<UsersBean>    users;
        public List<ZhfListBean>  zhfList;
        public List<CommentsBean> comments;

        public static class AdinfoBean implements Serializable {
            /**
             * id : 2
             * title : 广告2
             * imgs : /Uploads/2018-05-16/5afc20d69dd35.jpg
             * content : 广告2的内容
             * addtime : 1526472918
             */

            public String id;
            public String title;
            public String imgs;
            public String content;
            public String addtime;
        }

        public static class UsersBean implements Serializable {
            /**
             * id : 5
             * avatar : /Uploads/2018-05-21/5b02632067c44.jpg
             */

            public String id;
            public String avatar;
        }

        //        {
        //            "id":"1",
        //                "name":"wangLin",
        //                "avatar":"/Uploads/2018-05-17/5afd701ade84e.jpg"
        //        }

        public static class ZxfInfo implements Serializable {
            public int    id;
            public String name;
            public String avatar;
        }


        public static class ZhfListBean implements Serializable {
            /**
             * id : 1
             * name : wangLin
             * avatar : /Uploads/2018-05-17/5afd701ade84e.jpg
             * material : xxx
             */

            public String id;
            public String name;
            public String avatar;
            public String material;
        }

        public static class CommentsBean implements Serializable {
            /**
             * addtime : 1526871867
             * content : 用户5回复用户1
             * name : 张三
             * avatar : /Uploads/2018-05-21/5b02632067c44.jpg
             */

            public String addtime;
            public String content;
            public String name;
            public String avatar;
        }
    }
}
