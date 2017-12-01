package base;

/**
 * Created by DELL on 2017/11/14.
 */

public class BasePresenter<V extends BaseView> {
    public V mView;
    /**
     * 绑定P层
     * @param mView
     */
    public BasePresenter(V mView) {
        this.mView = mView;
    }

    /**
     * 解绑
     */
    public void deatach(){
        this.mView=null;
    }
}
