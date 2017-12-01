package view;

import base.BaseView;
import bean.UpLoad;
import bean.UpdateNickname;

/**
 * Created by DELL on 2017/11/30.
 */

public interface ChangUserView extends BaseView {

    void changnameSuccess(UpdateNickname updateNickname);
    void changnameFailure(UpdateNickname updateNickname);
    void uploadSuccess(UpLoad upLoad);
    void uploadFailure(UpLoad upLoad);
}
