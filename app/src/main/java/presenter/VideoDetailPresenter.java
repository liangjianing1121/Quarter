package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.VideoDetail;
import model.VideoDetailModel;
import view.VideoDetailView;

/**
 * Created by DELL on 2017/12/18.
 */

public class VideoDetailPresenter extends BasePresenter<VideoDetailView> implements VideoDetailModel.iGetVideoDetail {
    private Context context;
    private VideoDetailModel videoDetailModel;

    /**
     * 绑定P层
     *
     * @param mView
     */
    public VideoDetailPresenter(Context context,VideoDetailView mView) {
        super(mView);
        this.context=context;
        videoDetailModel=new VideoDetailModel();
        videoDetailModel.setiGetVideoDetail(this);
    }


    public  void getVideoDetail(String wid){
        videoDetailModel.getVideoDetail(wid);

    }

    @Override
    public void getVideoDetailSuccess(VideoDetail videoDetail) {
        mView.RequestSuccess(videoDetail);
    }

    @Override
    public void getVideoDetailFailure(VideoDetail videoDetail) {
        mView.RequestFailure(videoDetail);
    }
}
