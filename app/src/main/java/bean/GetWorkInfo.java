package bean;

import java.util.List;

/**
 * Created by DELL on 2017/12/15.
 */

public class GetWorkInfo {

    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : {"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"worksEntities":[{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151246552177120171205171834.jpg","createTime":"2017-12-05T17:18:41","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512465521771video_20171205_171828.mp4","wid":51,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151246557866220171205171834.jpg","createTime":"2017-12-05T17:19:38","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512465578662video_20171205_171828.mp4","wid":52,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151248086952120171205213419.jpg","createTime":"2017-12-05T21:34:29","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512480869521video_20171205_213345.mp4","wid":56,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151252157428720171206085247.jpg","createTime":"2017-12-06T08:52:54","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512521574287video_20171206_085203.mp4","wid":61,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151255002044320171206164618.jpg","createTime":"2017-12-06T16:47:00","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512550020443video_20171206_164500.mp4","wid":105,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151261880914620171207115305.jpg","createTime":"2017-12-07T11:53:29","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512618809146video_20171207_115248.mp4","wid":175,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151323769211520171214154759.jpg","createTime":"2017-12-14T15:48:12","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513237692115video_20171214_154742.mp4","wid":215,"workDesc":""}]}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * user : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"}
         * worksEntities : [{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151246552177120171205171834.jpg","createTime":"2017-12-05T17:18:41","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512465521771video_20171205_171828.mp4","wid":51,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151246557866220171205171834.jpg","createTime":"2017-12-05T17:19:38","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512465578662video_20171205_171828.mp4","wid":52,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151248086952120171205213419.jpg","createTime":"2017-12-05T21:34:29","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512480869521video_20171205_213345.mp4","wid":56,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151252157428720171206085247.jpg","createTime":"2017-12-06T08:52:54","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512521574287video_20171206_085203.mp4","wid":61,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151255002044320171206164618.jpg","createTime":"2017-12-06T16:47:00","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512550020443video_20171206_164500.mp4","wid":105,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151261880914620171207115305.jpg","createTime":"2017-12-07T11:53:29","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512618809146video_20171207_115248.mp4","wid":175,"workDesc":""},{"commentNum":null,"cover":"https://www.zhaoapi.cn/images/quarter/151323769211520171214154759.jpg","createTime":"2017-12-14T15:48:12","favoriteNum":null,"latitude":"100","localUri":null,"longitude":"100","playNum":null,"praiseNum":null,"uid":195,"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513237692115video_20171214_154742.mp4","wid":215,"workDesc":""}]
         */

        public UserBean user;
        public List<WorksEntitiesBean> worksEntities;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : false
             * icon : https://www.zhaoapi.cn/images/1512470827115mo.jpg
             * nickname : nnnnnn
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public boolean follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }

        public static class WorksEntitiesBean {
            /**
             * commentNum : null
             * cover : https://www.zhaoapi.cn/images/quarter/151246552177120171205171834.jpg
             * createTime : 2017-12-05T17:18:41
             * favoriteNum : null
             * latitude : 100
             * localUri : null
             * longitude : 100
             * playNum : null
             * praiseNum : null
             * uid : 195
             * videoUrl : https://www.zhaoapi.cn/images/quarter/1512465521771video_20171205_171828.mp4
             * wid : 51
             * workDesc :
             */

            public Object commentNum;
            public String cover;
            public String createTime;
            public Object favoriteNum;
            public String latitude;
            public Object localUri;
            public String longitude;
            public Object playNum;
            public Object praiseNum;
            public int uid;
            public String videoUrl;
            public int wid;
            public String workDesc;
        }
    }
}
