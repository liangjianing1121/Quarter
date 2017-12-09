package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.HotVideo;
import model.GetHotVideoModel;
import view.GetHotVideoView;

/**
 * Created by DELL on 2017/12/7.
 */

public class GetHotVideoPresenter extends BasePresenter<GetHotVideoView> implements GetHotVideoModel.iGetHotVideo {
    private GetHotVideoModel getHotVideoModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public GetHotVideoPresenter(Context context,GetHotVideoView mView) {
        super(mView);
        this.context=context;
        getHotVideoModel=new GetHotVideoModel();
        getHotVideoModel.setiGetHotVideo(this);

    }

    public void getHotVideo(String page){
        getHotVideoModel.getHotVideo(page);
    }

    @Override
    public void getHotVideoSuccess(HotVideo hotVideo) {
        mView.RequestSuccess(hotVideo);

    }

    @Override
    public void getHotVideoFailure(HotVideo hotVideo) {
        mView.RequestFailure(hotVideo);

    }
}
