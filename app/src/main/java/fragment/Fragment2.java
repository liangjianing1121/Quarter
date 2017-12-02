package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.quarter.OtherLoginActivity;
import com.example.dell.quarter.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.RvAdapter;
import bean.Duanzi;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.DuanPresenter;
import view.DuanListView;

/**
 * Created by DELL on 2017/11/23.
 */

public class Fragment2 extends Fragment implements DuanListView {
    private View view;
    private int i=1;
    private List<Duanzi.DataBean> list;
    private DuanPresenter presenter;
    private RvAdapter rvAdapter;
    private XRecyclerView xrv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
            view = inflater.inflate(R.layout.fragment2,null);
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
        initview();
        initData();
    }

    private void initview() {
        xrv = view.findViewById(R.id.xrv);
    }


    private void initData() {
        list = new ArrayList<>();
        presenter = new DuanPresenter(this,getActivity());
        presenter.getDuanList(1);

        xrv.setLoadingMoreEnabled(true);
        xrv.setPullRefreshEnabled(true);
        xrv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        xrv.setLayoutManager(linearLayoutManager);

    }



    @Override
    public void RequestSuccess(Duanzi duanzi) {
        String code = duanzi.code;
        if("2".equals(code)) {
            Intent intent = new Intent(getContext(), OtherLoginActivity.class);
            startActivity(intent);
        }
        list.addAll(duanzi.data);
        if(rvAdapter == null)
        {
            rvAdapter = new RvAdapter(getContext(),list);
            xrv.setAdapter(rvAdapter);
        }
        else
        {
            rvAdapter.notifyDataSetChanged();
        }
        xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                presenter.getDuanList(1);
                xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                i++;
                presenter.getDuanList(i);
                xrv.loadMoreComplete();
            }
        });

    }

    @Override
    public void RequestFailure(Duanzi duanzi) {

    }

    @Override
    public void Failure(Duanzi duanzi) {

    }
}
