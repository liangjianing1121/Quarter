package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.quarter.ChangeUserActivity;
import com.example.dell.quarter.R;
import com.example.dell.quarter.SettingActivity;
import com.facebook.drawee.view.SimpleDraweeView;


import bean.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;
import presenter.UserPresenter;
import view.UserView;

/**
 * Created by DELL on 2017/11/25.
 */

public class LeftFragment extends Fragment implements UserView, View.OnClickListener {



    private SimpleDraweeView ivHead;
    private TextView tvName;
    private LinearLayout llShezhi;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
            view = inflater.inflate(R.layout.leftfragment,null);
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
        EventBus.getDefault().register(this);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        UserPresenter presenter = new UserPresenter(this, getContext());
        SharedPreferences uidsp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        int uid = uidsp.getInt("uid", 0);
      //  presenter.getUser(uid + "");

    }

    private void initView() {
      ivHead = view.findViewById(R.id.iv_head);
      tvName=view.findViewById(R.id.tv_leftname);
      llShezhi = view.findViewById(R.id.ll_shezhi);


      ivHead.setOnClickListener(this);
      llShezhi.setOnClickListener(this);
    }

    private void initData() {


    }

    @Override
    public void RequestSuccess(User user) {
       /* User.DataBean data = user.data;
        String nickname = data.nickname;
        String icon = data.icon;
        user1 = user;

        ivHead.setImageURI(icon);
        tvName.setText(nickname);*/

        EventBus.getDefault().postSticky(user);
    }

    @Override
    public void RequestFailure(User user) {

    }

    @Override
    public void Failure(User user) {

    }


    @OnClick(R.id.ll_shezhi)
    public void onViewClicked() {
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shezhi:
                Intent intent=new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_head:
                Intent intent1=new Intent(getContext(), ChangeUserActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MainThread,sticky = true)
    public void onEvent(User user){
        System.out.println(user.data.icon+user.data.nickname);
        ivHead.setImageURI(user.data.icon);
        tvName.setText(user.data.nickname);
    }
}

