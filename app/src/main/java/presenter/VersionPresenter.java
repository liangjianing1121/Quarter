package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.Version;
import model.VersionModel;
import view.VersionView;

/**
 * Created by DELL on 2017/12/1.
 */

public class VersionPresenter extends BasePresenter<VersionView> implements VersionModel.igetVersion {
    private Context context;
    private VersionModel versionModel;

    /**
     * 绑定P层
     *
     * @param mView
     */
    public VersionPresenter(Context context,VersionView mView) {
        super(mView);
        this.context=context;
        versionModel=new VersionModel();
        versionModel.setIgetVersion(this);
    }

    public void getVersion(){
        versionModel.getVersion();
    }


    @Override
    public void getVerxionSuccess(Version version) {
        mView.RequestSuccess(version);
    }

    @Override
    public void getVersionFailure(Version version) {
        mView.RequestFailure(version);

    }
}
