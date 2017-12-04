package bean;

import java.util.List;

/**
 * Created by DELL on 2017/12/2.
 */

public class Videos {


    /**
     * msg : 获取作品列表成功
     * code : 0
     * data : [{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1512184570725a.jpg","createTime":"2017-12-02T11:16:10","favoriteNum":0,"latitude":"30","localUri":null,"longitude":"30","playNum":null,"praiseNum":0,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/1512184570725PictureSelector_20171202_111527.mp4","wid":14,"workDesc":"Y"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/e.png","createTime":"2017-12-02T11:01:23","favoriteNum":0,"latitude":"1221","localUri":null,"longitude":"4542","playNum":null,"praiseNum":0,"uid":2997,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512108751006e.png","nickname":"李薇","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/wx_camera_1509596718115.mp4","wid":13,"workDesc":"成功成功"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/PictureSelector_20171202_104440.mp4","createTime":"2017-12-02T10:44:57","favoriteNum":0,"latitude":"30","localUri":null,"longitude":"30","playNum":null,"praiseNum":0,"uid":145,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/a.jpg","wid":12,"workDesc":"Y"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/oppo3.3gp","createTime":"2017-12-01T21:35:21","favoriteNum":0,"latitude":"103","localUri":null,"longitude":"102","playNum":null,"praiseNum":0,"uid":115,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/115.jpg","nickname":"微风～","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/PictureSelector_20171201_133412.mp4","wid":11,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/1511410355963.jpeg","createTime":"2017-12-01T21:29:30","favoriteNum":0,"latitude":"45","localUri":null,"longitude":"190","playNum":null,"praiseNum":0,"uid":144,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512101463178cropped_1512101463933.jpg","nickname":"乔君","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/PictureSelector_20171201_212852.mp4","wid":10,"workDesc":null},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/ic_launcher.png","createTime":"2017-12-01T21:09:08","favoriteNum":0,"latitude":"103","localUri":null,"longitude":"102","playNum":null,"praiseNum":0,"uid":115,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/115.jpg","nickname":"微风～","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/oppo.mp4","wid":9,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","createTime":"2017-12-01T20:42:49","favoriteNum":0,"latitude":"1212","localUri":null,"longitude":"12121","playNum":null,"praiseNum":0,"uid":169,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","wid":8,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","createTime":"2017-12-01T20:41:42","favoriteNum":0,"latitude":"1212","localUri":null,"longitude":"12121","playNum":null,"praiseNum":0,"uid":169,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","wid":7,"workDesc":"fff"},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/d.jpg","createTime":"2017-12-01T19:45:51","favoriteNum":0,"latitude":"0.0","localUri":null,"longitude":"0.0","playNum":null,"praiseNum":0,"uid":551,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/551.jpg","nickname":"_zhang","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/x.mp4","wid":6,"workDesc":null},{"commentNum":0,"comments":[],"cover":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","createTime":"2017-12-01T19:36:57","favoriteNum":0,"latitude":"1212","localUri":null,"longitude":"12121","playNum":null,"praiseNum":0,"uid":169,"user":{"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/1512120820443cropped_1512120819548.jpg","nickname":"不熟","praiseNum":"null"},"videoUrl":"https://www.zhaoapi.cn/images/quarter/录屏_20171201_190404.mp4","wid":5,"workDesc":null}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * commentNum : 0
         * comments : []
         * cover : https://www.zhaoapi.cn/images/quarter/1512184570725a.jpg
         * createTime : 2017-12-02T11:16:10
         * favoriteNum : 0
         * latitude : 30
         * localUri : null
         * longitude : 30
         * playNum : null
         * praiseNum : 0
         * uid : 145
         * user : {"age":null,"fans":"null","follow":"null","icon":"https://www.zhaoapi.cn/images/145.jpg","nickname":null,"praiseNum":"null"}
         * videoUrl : https://www.zhaoapi.cn/images/quarter/1512184570725PictureSelector_20171202_111527.mp4
         * wid : 14
         * workDesc : Y
         */

        public int commentNum;
        public String cover;
        public String createTime;
        public int favoriteNum;
        public String latitude;
        public Object localUri;
        public String longitude;
        public Object playNum;
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
             * follow : null
             * icon : https://www.zhaoapi.cn/images/145.jpg
             * nickname : null
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public String follow;
            public String icon;
            public Object nickname;
            public String praiseNum;
        }
    }
}
