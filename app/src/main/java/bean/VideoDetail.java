package bean;

import java.util.List;

/**
 * Created by DELL on 2017/12/18.
 */

public class VideoDetail {


    /**
     * msg : 获取详情成功
     * code : 0
     * data : {"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1512618712631截屏_20171125_160558.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":0,"latitude":"40.040335","localUri":null,"longitude":"116.299891","playNum":4,"praiseNum":0,"uid":86,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/86.jpg","nickname":"GUODONG.","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/15126187126311507345866218.mp4","wid":174,"workDesc":"在人间"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * commentNum : 0
         * comments : []
         * cover : https://www.zhaoapi.cn/images/quarter/1512618712631截屏_20171125_160558.jpg
         * createTime : 2017-12-17T19:20:44
         * favoriteNum : 0
         * latitude : 40.040335
         * localUri : null
         * longitude : 116.299891
         * playNum : 4
         * praiseNum : 0
         * uid : 86
         * user : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/86.jpg","nickname":"GUODONG.","praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/images/quarter/15126187126311507345866218.mp4
         * wid : 174
         * workDesc : 在人间
         */

        public int commentNum;
        public String cover;
        public String createTime;
        public int favoriteNum;
        public String latitude;
        public Object localUri;
        public String longitude;
        public int playNum;
        public int praiseNum;
        public int uid;
        public UserBean user;
        public String videoUrl;
        public int wid;
        public String workDesc;
        public List<?> comments;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : false
             * icon : https://www.zhaoapi.cn/images/86.jpg
             * nickname : GUODONG.
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public boolean follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }
    }
}
