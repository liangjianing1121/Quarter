package com.example.dell.quarter;

import java.util.List;

/**
 * Created by DELL on 2017/12/14.
 */

public class GetFollowUser {

    /**
     * msg : 获取关注用户列表成功
     * code : 0
     * data : [{"age":null,"appkey":"20e2032e7281061c","appsecret":"6F4B84F2A709D497D64396A668722E10","createtime":"2017-12-13T15:03:05","email":null,"fans":null,"follow":null,"gender":null,"icon":"https://www.zhaoapi.cn/images/1513148630412image.jpg","latitude":null,"longitude":null,"mobile":"15302569391","money":null,"nickname":"14","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"941B303925422F66C10C53494EA7C64D","uid":3028,"userId":null,"username":"15302569391"}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * age : null
         * appkey : 20e2032e7281061c
         * appsecret : 6F4B84F2A709D497D64396A668722E10
         * createtime : 2017-12-13T15:03:05
         * email : null
         * fans : null
         * follow : null
         * gender : null
         * icon : https://www.zhaoapi.cn/images/1513148630412image.jpg
         * latitude : null
         * longitude : null
         * mobile : 15302569391
         * money : null
         * nickname : 14
         * password : 8F669074CAF5513351A2DE5CC22AC04C
         * praiseNum : null
         * token : 941B303925422F66C10C53494EA7C64D
         * uid : 3028
         * userId : null
         * username : 15302569391
         */

        public Object age;
        public String appkey;
        public String appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public Object gender;
        public String icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public Object money;
        public String nickname;
        public String password;
        public Object praiseNum;
        public String token;
        public int uid;
        public Object userId;
        public String username;
    }
}
