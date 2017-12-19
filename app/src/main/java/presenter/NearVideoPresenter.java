package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.NearVideos;
import fragment.FujinFragment;
import model.NearVideoModel;
import view.NearVideoView;

/**
 * Created by DELL on 2017/12/19.
 */

public class NearVideoPresenter extends BasePresenter<NearVideoView> implements NearVideoModel.iGetNearVideo {
    private Context context;
    private NearVideoModel nearVideoModel;


    /**
     * 绑定P层
     *
     * @param mView
     */
    public NearVideoPresenter(Context context,NearVideoView mView) {
        super(mView);
        this.context=context;
        nearVideoModel=new NearVideoModel();
        nearVideoModel.setiGetNearVideo(this);
    }


    public void getNearVideo(String page,String latitude,String longitude){
        nearVideoModel.getNearVideo(page,latitude,longitude);
    }

    @Override
    public void getNearVideoSuccess(NearVideos nearVideos) {
        mView.RequestSuccess(nearVideos);
    }

    @Override
    public void getNearVideoFailure(NearVideos nearVideos) {
        mView.RequestFailure(nearVideos);
    }
}
