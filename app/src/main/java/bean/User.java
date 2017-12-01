package bean;

/**
 * Created by DELL on 2017/11/28.
 */

public class User {


    /**
     * msg : 获取用户信息成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-27T20:37:17","email":null,"fans":0,"follow":0,"gender":0,"icon":"https://www.zhaoapi.cn/images/195.jpg","latitude":null,"longitude":null,"mobile":"18410261121","money":0,"nickname":"梁佳宁","password":"111111","praiseNum":null,"token":"C5F1896E305FA85630AA9BB54A621BC3","uid":195,"username":"18410261121"}
     */

    public String msg;
    public String code;
    public DataBean data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-27T20:37:17
         * email : null
         * fans : 0
         * follow : 0
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/195.jpg
         * latitude : null
         * longitude : null
         * mobile : 18410261121
         * money : 0
         * nickname : 梁佳宁
         * password : 111111
         * praiseNum : null
         * token : C5F1896E305FA85630AA9BB54A621BC3
         * uid : 195
         * username : 18410261121
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public int fans;
        public int follow;
        public int gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public int money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public String username;
    }
}
