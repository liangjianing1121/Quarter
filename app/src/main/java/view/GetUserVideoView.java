package view;

import base.BaseView;
import bean.Follow;
import bean.User;
import bean.UserVideos;

/**
 * Created by DELL on 2017/12/6.
 */

public interface GetUserVideoView  extends  BaseView{
    void getUserVideosSuccess(UserVideos userVideos);
    void getUserVideoFailure(UserVideos videos);

    void getUserSuccess(User user);
    void getUserFailure(User user);

    void FollowSuccess(Follow follow);
    void FollowFailure(Follow follow);
}
