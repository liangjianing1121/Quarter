package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.RandomFriend;
import bean.SearchFriend;
import model.FriendModel;
import view.FriendView;

/**
 * Created by DELL on 2017/12/16.
 */

public class FriendPresenter extends BasePresenter<FriendView> implements FriendModel.iRandomFriend {

    private Context context;
    private FriendModel friendModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public FriendPresenter(Context context,FriendView mView) {
        super(mView);
        this.context=context;
        friendModel=new FriendModel();
        friendModel.setiRandomFriend(this);
    }

    public void getRandomFriend(){
        friendModel.randomFriends();
    }

    public void searchFriend(String keywords,String page){
        friendModel.searchFriend(keywords,page);
    }


    @Override
    public void RandomFriendSuccess(RandomFriend randomFriend) {
        mView.RandomFriendSuccess(randomFriend);
    }

    @Override
    public void RandomFriendFailure(RandomFriend randomFriend) {
        mView.RandomFriendFailure(randomFriend);
    }

    @Override
    public void SearchFriendSuccess(SearchFriend searchFriend) {
        mView.SearchFriendSuccess(searchFriend);
    }

    @Override
    public void SearchFriendFailure(SearchFriend searchFriend) {
        mView.SearchFriendFailure(searchFriend);
    }
}
