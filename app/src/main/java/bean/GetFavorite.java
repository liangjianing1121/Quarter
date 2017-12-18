package bean;

import java.util.List;

/**
 * Created by DELL on 2017/12/18.
 */

public class GetFavorite {

    /**
     * msg : 获取收藏列表成功
     * code : 0
     * data : [{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1512902776943截屏_20170710_235930.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":1,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":0,"praiseNum":2,"uid":185,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512902776943video_20171210_184530.mp4","wid":205,"workDesc":null},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/151297306717858db6cd8e7bce70f0bd60511.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":2,"latitude":"40","localUri":null,"longitude":"50","playNum":0,"praiseNum":1,"uid":77,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/15129730671781512896831835.mp4","wid":209,"workDesc":"学习学习"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/15129591508500.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":1,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":0,"praiseNum":3,"uid":150,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/15129591508501512892442603.mp4","wid":206,"workDesc":"流浪"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1512902759146截屏_20170710_235930.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":1,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":0,"praiseNum":2,"uid":185,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/15129027591461-Android系列视频开篇之作 - 副本.mp4","wid":204,"workDesc":null},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/15129709971461512195499099.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":2,"latitude":"50","localUri":null,"longitude":"500","playNum":0,"praiseNum":2,"uid":313,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512970997146PictureSelector_20171207_162143.mp4","wid":208,"workDesc":"---"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/151296375795920171211114218.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":2,"latitude":"40.040565","localUri":null,"longitude":"116.300107","playNum":0,"praiseNum":3,"uid":3028,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512963757959sccvideo.mp4","wid":207,"workDesc":null},{"commentNum":2,"comments":[{"cid":16,"content":"%E7%9C%9F%E6%A3%92","createTime":"2017-12-13T14:53:20","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":210},{"cid":17,"content":"%E4%BD%A0","createTime":"2017-12-13T14:55:06","jid":null,"mvp":null,"nickname":"beautiful","praiseNum":0,"uid":170,"wid":210}],"cover":"https://www.zhaoapi.cn/images/quarter/151313435741220171213110540.jpg","createTime":"2017-12-17T19:20:44","favoriteNum":1,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":0,"praiseNum":1,"uid":2956,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1513134357412cmhvideo.mp4","wid":210,"workDesc":"这是视频是视频"}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * commentNum : 0
         * comments : []
         * cover : https://www.zhaoapi.cn/images/quarter/1512902776943截屏_20170710_235930.jpg
         * createTime : 2017-12-17T19:20:44
         * favoriteNum : 1
         * latitude : 0.0
         * localUri : null
         * longitude : 0.0
         * playNum : 0
         * praiseNum : 2
         * uid : 185
         * user : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","nickname":"nnnnnn","praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512902776943video_20171210_184530.mp4
         * wid : 205
         * workDesc : null
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
        public Object workDesc;
        public List<?> comments;

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
    }
}
