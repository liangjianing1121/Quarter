package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.Follow;
import bean.User;
import bean.UserVideos;
import model.GetUserVideosModel;
import view.GetUserVideoView;

/**
 * Created by DELL on 2017/12/6.
 */

public class GetUserVideoPresenter extends BasePresenter<GetUserVideoView> implements GetUserVideosModel.iGetUserVideos {
    private GetUserVideosModel getUserVideosModel;
    private Context context;

    /**
     * 绑定P层
     *
     * @param mView
     */
    public GetUserVideoPresenter(Context context,GetUserVideoView mView) {
        super(mView);
        this.context=context;
        getUserVideosModel=new GetUserVideosModel();
        getUserVideosModel.setiGetUserVideos(this);
    }


    public void getUserVideo(String uid,String page){
        getUserVideosModel.getUserVideo(uid,page);

    }

    public void getUser(String uid){
        getUserVideosModel.getUser(uid);
    }

    public  void  getFollow(String uid, String followid){
        getUserVideosModel.getFollow(uid,followid);
    }

    @Override
    public void getUserVideosSuccess(UserVideos userVideos) {
        mView.getUserVideosSuccess(userVideos);
    }

    @Override
    public void getUserVideoFailure(UserVideos videos) {
        mView.getUserVideoFailure(videos);
    }

    @Override
    public void getUserSuccess(User user) {
        mView.getUserSuccess(user);
    }

    @Override
    public void getUserFailure(User user) {
        mView.getUserFailure(user);
    }

    @Override
    public void FollowSuccess(Follow follow) {
        mView.FollowSuccess(follow);
    }

    @Override
    public void FollowFailure(Follow follow) {
        mView.FollowFailure(follow);
    }
}
