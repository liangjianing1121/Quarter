package presenter;

import android.content.Context;

import com.example.dell.quarter.BaseActivity;

import java.io.File;

import base.BasePresenter;
import bean.PublishJoke;
import bean.PublishVideos;
import model.PublishJokeModel;
import model.PublishVideosModel;
import view.PublishVideoView;

/**
 * Created by DELL on 2017/12/5.
 */

public class PublishVideoPresenter extends BasePresenter<PublishVideoView> implements PublishVideosModel.iPublishVideo {
    private Context context;
    private PublishVideosModel publishVideosModel;

    /**
     * 绑定P层
     *
     * @param mView
     */
    public PublishVideoPresenter(Context context,PublishVideoView mView) {
        super(mView);
        this.context=context;
        publishVideosModel=new PublishVideosModel();
        publishVideosModel.setiPublishVideo(this);
    }

    public void publishVideo(String uid, File videoFile, File coverFile,
                             String videoDesc, String latitude, String longitude){
        publishVideosModel.pulishVideos(uid,videoFile,coverFile,videoDesc,latitude,longitude);
    }


    @Override
    public void publishVoideoSuccess(PublishVideos publishVideos) {
        mView.RequestSuccess(publishVideos);

    }

    @Override
    public void publishVideoFailure(PublishVideos publishVideos) {
        mView.RequestFailure(publishVideos);
    }
}
