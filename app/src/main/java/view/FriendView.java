package view;

import base.BaseView;
import bean.RandomFriend;

/**
 * Created by DELL on 2017/12/16.
 */

public interface FriendView extends BaseView {

    void RandomFriendSuccess(RandomFriend randomFriend);
    void RandomFriendFailure(RandomFriend randomFriend);


}
