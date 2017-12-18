package fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransitionImpl;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.quarter.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.leakcanary.DisplayLeakService;

import java.util.ArrayList;
import java.util.List;

import adapter.ShangChuanAdapter;
import bean.Follow;
import bean.User;
import bean.UserVideos;
import presenter.GetUserVideoPresenter;
import view.GetUserVideoView;

/**
 * Created by DELL on 2017/12/7.
 */

public class ShangChuanFragment extends Fragment implements GetUserVideoView {


    private View view;
    private XRecyclerView xrv;
    private int i=1;
    private ShangChuanAdapter shangChuanAdapter;
    private   List<UserVideos.DataBean> list;
    private GetUserVideoPresenter presenter;
    private int uid1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.shangchuanfragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list=new ArrayList<>();
        xrv = view.findViewById(R.id.xrv);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),3);
        xrv.setLayoutManager(gridLayoutManager);

        presenter = new GetUserVideoPresenter(getActivity(),this);

        SharedPreferences uid = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid1 = uid.getInt("uid", 1);

        presenter.getUserVideo(uid1 +"",i+"");
    }

    @Override
    public void RequestSuccess(Object o) {

    }

    @Override
    public void RequestFailure(Object o) {

    }

    @Override
    public void Failure(Object o) {

    }

    @Override
    public void getUserVideosSuccess(UserVideos userVideos) {
        List<UserVideos.DataBean> data = userVideos.data;
        list.addAll(data);
        if(shangChuanAdapter==null)
        {
            shangChuanAdapter=new ShangChuanAdapter(getActivity(),list);
            xrv.setAdapter(shangChuanAdapter);
        }
        else
        {
            shangChuanAdapter.notifyDataSetChanged();
        }

        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                presenter.getUserVideo(uid1+"",1+"");
            }

            @Override
            public void onLoadMore() {
                i++;
                presenter.getUserVideo(uid1+"",i+"");
            }
        });
    }

    @Override
    public void getUserVideoFailure(UserVideos videos) {

    }

    @Override
    public void getUserSuccess(User user) {

    }

    @Override
    public void getUserFailure(User user) {

    }

    @Override
    public void FollowSuccess(Follow follow) {

    }

    @Override
    public void FollowFailure(Follow follow) {

    }
}
