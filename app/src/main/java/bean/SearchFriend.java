package bean;

import java.util.List;

/**
 * Created by DELL on 2017/12/18.
 */

public class SearchFriend {


    /**
     * msg : 查询成功
     * code : 0
     * data : [{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-18T14:38:17","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/151263717022520171207165918.jpg","latitude":null,"longitude":null,"mobile":"15176046561","money":0,"nickname":"jinlin","password":"666666","praiseNum":null,"token":"DED0233723EC79592080E46729E8F905","uid":92,"userId":null,"username":"15176046561"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-18T18:53:49","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/86.jpg","latitude":null,"longitude":null,"mobile":"15011411273","money":0,"nickname":"GUODONG.","password":"111111","praiseNum":null,"token":"C4BF90E97420953ECE276A0D1287AFC0","uid":86,"userId":null,"username":"15011411273"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-18T18:47:09","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1512470827115mo.jpg","latitude":null,"longitude":null,"mobile":"18410261121","money":0,"nickname":"nnnnnn","password":"111111","praiseNum":null,"token":"C5F1896E305FA85630AA9BB54A621BC3","uid":195,"userId":null,"username":"18410261121"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-10-14T12:01:08","email":null,"fans":null,"follow":null,"gender":0,"icon":null,"latitude":null,"longitude":null,"mobile":"13555481003","money":0,"nickname":"myNewNickName","password":"123456","praiseNum":null,"token":null,"uid":528,"userId":null,"username":"13555481003"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-06T14:38:50","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/1513336144369binbin.png","latitude":null,"longitude":null,"mobile":"15710055961","money":0,"nickname":"更改的Username","password":"123456","praiseNum":null,"token":"816AD1DFDBE2EDE1EF8B5462F1FEE5C6","uid":536,"userId":null,"username":"15710055961"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-12-18T15:43:06","email":null,"fans":null,"follow":null,"gender":0,"icon":"https://www.zhaoapi.cn/images/551.jpg","latitude":null,"longitude":null,"mobile":"15988888888","money":0,"nickname":"_zhang","password":"111111","praiseNum":null,"token":"AD7D76CB63F7A5E17C64A14A4FE588D1","uid":551,"userId":null,"username":"15988888888"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-10-19T00:00:00","email":null,"fans":null,"follow":null,"gender":0,"icon":null,"latitude":null,"longitude":null,"mobile":"18510588447","money":0,"nickname":"wutong","password":"123456","praiseNum":null,"token":null,"uid":951,"userId":null,"username":"18510588447"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-24T20:44:29","email":null,"fans":null,"follow":null,"gender":0,"icon":null,"latitude":null,"longitude":null,"mobile":"18510588888","money":0,"nickname":"wutong","password":"123456","praiseNum":null,"token":"86C5C23F5430ED3091E8B9FCADCB4395","uid":1052,"userId":null,"username":"18510588888"},{"age":null,"appkey":null,"appsecret":null,"createtime":"2017-10-20T00:00:00","email":null,"fans":null,"follow":null,"gender":0,"icon":"http://120.27.23.105/images/1080.jpg","latitude":null,"longitude":null,"mobile":"15034434554","money":0,"nickname":"bihongxin","password":"123456","praiseNum":null,"token":null,"uid":1080,"userId":null,"username":"15034434554"},{"age":null,"appkey":"494a308fe5295da6","appsecret":"7FE28044EA1C0BE385C726F77035638A","createtime":"2017-12-16T15:30:21","email":null,"fans":null,"follow":null,"gender":null,"icon":null,"latitude":null,"longitude":null,"mobile":"15340986701","money":null,"nickname":"yangshuan","password":"8F669074CAF5513351A2DE5CC22AC04C","praiseNum":null,"token":"A93CCCF37F99C6AB83E0FF9193735DF3","uid":1259,"userId":null,"username":"15340986701"}]
     * page : -1
     */

    public String msg;
    public String code;
    public int page;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-12-18T14:38:17
         * email : null
         * fans : null
         * follow : null
         * gender : 0
         * icon : https://www.zhaoapi.cn/images/151263717022520171207165918.jpg
         * latitude : null
         * longitude : null
         * mobile : 15176046561
         * money : 0
         * nickname : jinlin
         * password : 666666
         * praiseNum : null
         * token : DED0233723EC79592080E46729E8F905
         * uid : 92
         * userId : null
         * username : 15176046561
         */

        public Object age;
        public Object appkey;
        public Object appsecret;
        public String createtime;
        public Object email;
        public Object fans;
        public Object follow;
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
        public Object userId;
        public String username;
    }
}
