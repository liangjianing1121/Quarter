package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.quarter.R;
import com.facebook.common.internal.Objects;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.ReMen3Adapter;
import bean.Ad;
import bean.HotVideo;
import bean.Videos;
import presenter.GetHotVideoPresenter;
import presenter.GetVideosPresenter;
import view.GetHotVideoView;
import view.GetVoidesView;

/**
 * Created by DELL on 2017/11/27.
 */

public class RemenFragment3 extends Fragment implements GetHotVideoView {

    private View view;
    private XRecyclerView xrv;
    private ReMen3Adapter reMen3Adapter;
    private List<HotVideo.DataBean> list;
    private GetHotVideoPresenter presenter;
    private int i;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null)
        {
            view = inflater.inflate(R.layout.remenfragment3,null);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null)
        {
            parent.removeView(view);
        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        list=new ArrayList<>();
        xrv.setPullRefreshEnabled(true);
        xrv.setLoadingMoreEnabled(true);
        xrv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void initView() {
        xrv = view.findViewById(R.id.xrv);
    }

    


    @Override
    public void onResume() {
        super.onResume();
        presenter = new GetHotVideoPresenter(getContext(),this);
        presenter.getHotVideo(1+"");
    }

    @Override
    public void RequestSuccess(HotVideo hotVideo) {
        Toast.makeText(getContext(), hotVideo.msg, Toast.LENGTH_SHORT).show();
        list.addAll(hotVideo.data);
        if(reMen3Adapter==null)
        {
            reMen3Adapter = new ReMen3Adapter(getContext(),list);
            xrv.setAdapter(reMen3Adapter);

        }
        else
        {
            reMen3Adapter.notifyDataSetChanged();
        }
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                presenter.getHotVideo(1+"");
                xrv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                i++;
                presenter.getHotVideo(i+"");
                xrv.loadMoreComplete();
            }
        });

    }

    @Override
    public void RequestFailure(HotVideo hotVideo) {

    }

    @Override
    public void Failure(HotVideo hotVideo) {

    }
}
