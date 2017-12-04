package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.Ad;
import bean.Videos;
import model.GetVideosModel;
import view.GetVoidesView;

/**
 * Created by DELL on 2017/12/2.
 */

public class GetVideosPresenter extends BasePresenter<GetVoidesView> implements GetVideosModel.igetVideos {
    private GetVideosModel getVideosModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public GetVideosPresenter(Context context,GetVoidesView mView) {
        super(mView);
        this.context=context;
        getVideosModel=new GetVideosModel();
        getVideosModel.setIgetVideos(this);
    }

    public void getVideos(String uid,String type,String page){
        getVideosModel.getVideo(uid,type,page);
    }


    public void  getAd(){
        getVideosModel.getAd();
    }

    @Override
    public void getVideosSuccess(Videos videos) {
        mView.getVideoSuccess(videos);
    }

    @Override
    public void getVideosFailure(Videos videos) {
        mView.getVideoFailure(videos);

    }

    @Override
    public void getAdSuccess(Ad ad) {
        mView.getAdSuccess(ad);

    }

    @Override
    public void getAdFailure(Ad ad) {
        mView.getAdFailure(ad);

    }
}
