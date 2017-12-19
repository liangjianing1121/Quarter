package view;

import base.BaseView;
import bean.RandomFriend;
import bean.SearchFriend;

/**
 * Created by DELL on 2017/12/16.
 */

public interface FriendView extends BaseView {

    void RandomFriendSuccess(RandomFriend randomFriend);
    void RandomFriendFailure(RandomFriend randomFriend);


    void SearchFriendSuccess(SearchFriend searchFriend);
    void SearchFriendFailure(SearchFriend searchFriend);

}
