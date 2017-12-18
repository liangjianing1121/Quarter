package presenter;

import android.content.Context;

import base.BasePresenter;
import bean.BaseBean;
import model.CommentModel;
import view.CommentView;

/**
 * Created by DELL on 2017/12/14.
 */

public class CommentPresenter extends BasePresenter<CommentView> implements CommentModel.getComment {
    private CommentModel commentModel;
    private Context context;
    /**
     * 绑定P层
     *
     * @param mView
     */
    public CommentPresenter(Context context,CommentView mView) {
        super(mView);
        this.context=context;
        commentModel=new CommentModel();
        commentModel.setGetComment(this);

    }

    public void getComment(String uid,String wid,String conent){
        commentModel.putComment(uid,wid,conent);
    }


    @Override
    public void CommonSuccess(BaseBean baseBean) {
        mView.RequestSuccess(baseBean);

    }

    @Override
    public void commonFailure(BaseBean baseBean) {
        mView.RequestFailure(baseBean);
    }
}
