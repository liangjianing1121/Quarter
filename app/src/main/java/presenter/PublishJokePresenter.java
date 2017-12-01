package presenter;

import android.content.Context;

import java.io.File;
import java.util.List;

import base.BasePresenter;
import bean.PublishJoke;
import model.PublishJokeModel;
import view.PublishJokeview;

/**
 * Created by DELL on 2017/11/28.
 */

public class PublishJokePresenter extends BasePresenter<PublishJokeview> implements PublishJokeModel.publishjokemsg {
    private Context context;
    private PublishJokeModel publishJokeModel;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public PublishJokePresenter(PublishJokeview mView,Context context) {
        super(mView);
        this.context=context;
        publishJokeModel=new PublishJokeModel();
        publishJokeModel.setPublishjokemsg(this);
    }
    public void getPublishJoke(String uid, String content,List<String> path){
        publishJokeModel.getupdateJoke(uid,content,path);
    }


    @Override
    public void publishjokemsgSuccess(PublishJoke publishJoke) {
        mView.RequestSuccess(publishJoke);
    }

    @Override
    public void publishjokemsgFailure(PublishJoke publishJoke) {
        mView.RequestFailure(publishJoke);
    }
}
