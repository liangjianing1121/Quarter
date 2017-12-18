package bean;

import java.util.List;

/**
 * Created by DELL on 2017/12/16.
 */

public class RandomFriend  {

    /**
     * msg : 请求成功
     * code : 1
     * data : [{"age":null,"appkey":"71b93af88da10264","appsecret":"6D3CBA0AD2E2076BCD99863BAFB5D1A8","createtime":"2017-11-28T13:02:50","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"13701373035","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":null,"uid":2837,"userId":null,"username":"13701373035"},{"age":null,"appkey":"d8fe3c7df4554dab","appsecret":"A3C2B12B7F665DB646F314351304829B","createtime":"2017-12-06T09:37:06","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"15893267998","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"7B6754958DA69DAC6411DA0BF9BD2020","uid":2838,"userId":null,"username":"15893267998"},{"age":null,"appkey":"ab4bd730bc2e819d","appsecret":"D153BD1474017A72A5F9EFE79D4B5B04","createtime":"2017-11-28T13:07:01","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"15901515466","money":null,"nickname":null,"password":"00C4613761DBF903A58319DB090D0F80","praiseNum":null,"token":null,"uid":2839,"userId":null,"username":"15901515466"},{"age":null,"appkey":"76496fcf49085f21","appsecret":"1293EC96994D1062C015F214BE3AE95A","createtime":"2017-11-28T13:07:50","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"13201373035","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"B648AF49556DEDB68D75342A98EEE6AC","uid":2840,"userId":null,"username":"13201373035"},{"age":null,"appkey":"648d86602d14ac61","appsecret":"BC18E3B3DA7C4256F3DD65C177A4A36B","createtime":"2017-11-28T13:24:30","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"13701125265","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":null,"uid":2841,"userId":null,"username":"13701125265"},{"age":null,"appkey":"9b634c2a9c574610","appsecret":"10781D36AF1DB5AFF3D534AAA521DAE7","createtime":"2017-11-28T20:15:54","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"15850974805","money":null,"nickname":null,"password":"118763506757F91BDBB7CBEA981DE61F","praiseNum":null,"token":"37A3988736613040530B4723B86ED643","uid":2842,"userId":null,"username":"15850974805"},{"age":null,"appkey":"825a1df3ca308ad0","appsecret":"92B2FC21D17EEFA43678D9C99AC084F1","createtime":"2017-11-28T13:29:34","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"17316226397","money":null,"nickname":null,"password":"231958AAD5E05BBCC66E9D21C505837D","praiseNum":null,"token":null,"uid":2843,"userId":null,"username":"17316226397"},{"age":null,"appkey":"c91c2145201df18a","appsecret":"54C3E88129E7FEC083942B1F44828B0C","createtime":"2017-11-28T13:32:11","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"13021401974","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"8FF11BD7BF3AF560A975A59EF9D78678","uid":2844,"userId":null,"username":"13021401974"},{"age":null,"appkey":"fe4fb7bc667c7b30","appsecret":"0F76538FE474F0DC6C02A3BCEA6EDBC7","createtime":"2017-12-15T20:03:34","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"15901415166","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"36471BDA7A4BD22560CC9A207185CA65","uid":2845,"userId":null,"username":"15901415166"},{"age":null,"appkey":"601c69352c859c1f","appsecret":"433CE3530B92EEF18B0AAAD11991C381","createtime":"2017-11-28T13:32:54","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"13021401975","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":null,"uid":2846,"userId":null,"username":"13021401975"}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * age : null
         * appkey : 71b93af88da10264
         * appsecret : 6D3CBA0AD2E2076BCD99863BAFB5D1A8
         * createtime : 2017-11-28T13:02:50
         * email : null
         * fans : null
         * follow : null
         * gender : null
         * icon : null
         * latitude : null
         * longitude : null
         * mobile : 13701373035
         * money : null
         * nickname : null
         * password : 8F669074CAF5513351A2DE5CC22AC04C
         * praiseNum : null
         * token : null
         * uid : 2837
         * userId : null
         * username : 13701373035
         */

        public Object age;
        public String appkey;
        public String appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
        public Object gender;
        public Object icon;
        public Object latitude;
        public Object longitude;
        public String mobile;
        public Object money;
        public Object nickname;
        public String password;
        public Object praiseNum;
        public Object token;
        public int uid;
        public Object userId;
        public String username;
    }
}
