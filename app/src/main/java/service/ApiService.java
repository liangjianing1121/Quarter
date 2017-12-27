package service;

import com.example.dell.quarter.GetFollowUser;

import java.util.List;

import bean.Ad;
import bean.AddFavorite;
import bean.BaseBean;
import bean.Duanzi;
import bean.Follow;
import bean.GetFavorite;
import bean.GetWorkInfo;
import bean.HotVideo;
import bean.Login;
import bean.NearVideos;
import bean.Praise;
import bean.PublishJoke;
import bean.PublishVideos;
import bean.RandomFriend;
import bean.Register;
import bean.SearchFriend;
import bean.UpLoad;
import bean.UpdateNickname;
import bean.User;
import bean.UserVideos;
import bean.Version;
import bean.VideoDetail;
import bean.Videos;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
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

    @POST("quarter/register")
    @FormUrlEncoded
    Observable<Register> getRegister(@Field("regType") String regType,@Field("mobile") String mobile, @Field("password") String password);


    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<User> getUser(@Field("uid") String uid);

  /*  @POST("quarter/getJokes")
    @FormUrlEncoded
    Observable<Duanzi> getduanlist(@Field("page") String page);*/


    @Headers("cache:20")
    @GET("quarter/getJokes")
    Observable<Duanzi> getduanlist(@Query("page") String page);


    @POST("quarter/publishJoke")
    @Multipart
    Observable<PublishJoke> updateJoke(@Part List<MultipartBody.Part> part);

    @POST("user/updateNickName")
    @FormUrlEncoded
    Observable<UpdateNickname> updatenickname(@Field("uid") String uid , @Field("nickname") String nickname);

    /*@POST("quarter/getAd")
    Observable<Ad> getAd();
*/

    @Headers("cache:20")
    @GET("quarter/getAd")
    Observable<Ad> getAd();

    @POST("file/upload")
    @Multipart
    Observable<UpLoad> upload(@Query("uid") String uid,@Part MultipartBody.Part img);

    @POST("quarter/getVersion")
    Observable<Version> getVersion();

    /*@POST("quarter/getVideos")
    @FormUrlEncoded
    Observable<Videos> getVideos(@Field("uid") String uid,@Field("type") String type, @Field("page") String page);
*/

    @Headers("cache:20")
    @GET("quarter/getVideos")
    Observable<Videos> getVideos(@Query("uid") String uid,@Query("type") String type, @Query("page") String page);

    @POST("quarter/publishVideo")
    @Multipart
    Observable<PublishVideos> publishVideo(@Part List<MultipartBody.Part> part);

    @POST("quarter/getUserVideos")
    @FormUrlEncoded
    Observable<UserVideos> getUserVideos(@Field("uid") String uid, @Field("page") String page);


    @POST("quarter/follow")
    @FormUrlEncoded
    Observable<Follow> geteFollow(@Field("uid") String uid,@Field("followId") String followid);


    /*@POST("quarter/getHotVideos")
    @FormUrlEncoded
    Observable<HotVideo> getHotVideo(@Field("page") String page);*/

    @Headers("cache:20")
    @GET("quarter/getHotVideos")
    Observable<HotVideo> getHotVideo(@Query("page") String page);

    @POST("quarter/praise")
    @FormUrlEncoded
    Observable<Praise> getPraise(@Field("uid") String uid,@Field("wid") String wid);

    @POST("quarter/addFavorite")
    @FormUrlEncoded
    Observable<AddFavorite> addFavorite(@Field("uid") String uid,@Field("wid") String wid);

    @POST("quarter/cancelFavorite")
    @FormUrlEncoded
    Observable<AddFavorite> cancelFavorite(@Field("uid") String uid,@Field("wid") String wid);

    @POST("quarter/comment")
    @FormUrlEncoded
    Observable<BaseBean> comment(@Field("uid") String uid,@Field("wid") String wid, @Field("content") String content);

    @Headers("cache:20")
    @GET("quarter/getFollowUsers")
    Observable<GetFollowUser> getFollowUser(@Query("uid") String uid);

    @POST("quarter/commentJoke")
    @FormUrlEncoded
    Observable<BaseBean> commentJoke(@Field("uid") String uid,@Field("jid") String jid,@Field("content") String content);


    @POST("quarter/getWorkInfo")
    @FormUrlEncoded
    Observable<GetWorkInfo> getWorkInfo(@Field("uid") String uid);

    @GET("quarter/randomFriends?")
    Observable<RandomFriend> getrandomFriends();

    @POST("quarter/getFavorites")
    @FormUrlEncoded
    Observable<GetFavorite> getFavorite(@Field("uid") String uid);

    @POST("quarter/getVideoDetail")
    @FormUrlEncoded
    Observable<VideoDetail> getVideoDetail(@Field("wid") String wid);

    @POST("quarter/searchFriends")
    @FormUrlEncoded
    Observable<SearchFriend> searchFriends(@Field("keywords") String keywords,@Field("page") String page);

    @POST("quarter/getNearVideos")
    @FormUrlEncoded
    Observable<NearVideos> getNearVideos(@Field("page") String page, @Field("latitude") String latitude,@Field("longitude") String longitude);


    @POST("quarter/removeWork")
    @FormUrlEncoded
    Observable<BaseBean> removeWork(@Field("uid") String uid,@Field("wid") String wid);



}
