package presenter;

import android.content.Context;
import android.view.ContextThemeWrapper;

import java.net.ConnectException;
import java.util.ConcurrentModificationException;

import base.BasePresenter;
import base.BaseView;
import bean.BaseBean;
import model.CommentJokeModel;
import view.CommentJokeView;

/**
 * Created by DELL on 2017/12/15.
 */

public class CommentJokePresenter extends BasePresenter<CommentJokeView> implements CommentJokeModel.iCommentJoke {
    private Context context;
    private CommentJokeModel commentJokeModel;

    /**
     * 绑定P层
     *
     * @param mView
     */
    public CommentJokePresenter(Context context,CommentJokeView mView) {
        super(mView);
        this.context=context;
        commentJokeModel=new CommentJokeModel();
        commentJokeModel.setiCommentJoke(this);
    }
    public void commentJoke(String uid,String jid,String content){
        commentJokeModel.commentJoke(uid,jid,content);
    }

    @Override
    public void CommentJokeSuccess(BaseBean baseBean) {

        mView.RequestSuccess(baseBean);
    }

    @Override
    public void CommentJokeFailure(BaseBean baseBean) {

        mView.RequestFailure(baseBean);
    }
}
