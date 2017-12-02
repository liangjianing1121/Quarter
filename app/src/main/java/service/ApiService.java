package service;

import java.util.List;

import bean.Ad;
import bean.Duanzi;
import bean.Login;
import bean.PublishJoke;
import bean.Register;
import bean.UpLoad;
import bean.UpdateNickname;
import bean.User;
import bean.Version;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by DELL on 2017/11/27.
 */

public interface ApiService {
    @POST("user/login")
    @FormUrlEncoded
    Observable<Login> getlogin(@Field("mobile") String mobile, @Field("password") String password);

    @POST("user/reg")
    @FormUrlEncoded
    Observable<Register> getRegister(@Field("mobile") String mobile, @Field("password") String password);


    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<User> getUser(@Field("uid") String uid);

    @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<Duanzi> getduanlist(@Field("page") String page);

    @POST("quarter/publishJoke")
    @Multipart
    Observable<PublishJoke> updateJoke(@Part List<MultipartBody.Part> part);



    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<UpdateNickname> updatenickname(@Field("uid") String uid , @Field("nickname") String nickname);

    @POST("quarter/getAd")
    Observable<Ad> getAd();


    @POST("file/upload")
    @Multipart
    Observable<UpLoad> upload(@Query("uid") String uid,@Part MultipartBody.Part img);

    @POST("quarter/getVersion")
    Observable<Version> getVersion();


}
