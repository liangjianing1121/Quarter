package fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import presenter.UserPresenter;
import view.UserView;

/**
 * Created by DELL on 2017/11/25.
 */

public class LeftFragment extends Fragment implements UserView {


    @BindView(R.id.iv_head)
    SimpleDraweeView ivHead;
    @BindView(R.id.tv_leftname)
    TextView tvName;
    Unbinder unbinder;
    @BindView(R.id.ll_shezhi)
    LinearLayout llShezhi;

    private View view;
    private TextView name;
    private User user1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.leftfragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        UserPresenter presenter = new UserPresenter(this, getContext());
        SharedPreferences uidsp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        int uid = uidsp.getInt("uid", 0);
        presenter.getUser(uid + "");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void RequestSuccess(User user) {
        User.DataBean data = user.data;
        String nickname = data.nickname;
        String icon = data.icon;
        user1 = user;

        ivHead.setImageURI(icon);
        tvName.setText(nickname);
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

    @OnClick({R.id.relativeLayout4, R.id.iv_head, R.id.textView3, R.id.tv_leftname, R.id.imageView6, R.id.imageView3, R.id.relativeLayout5, R.id.imageView2, R.id.relativeLayout6, R.id.imageView4, R.id.relativeLayout7, R.id.imageView5, R.id.relativeLayout8, R.id.imageView7, R.id.ll_shezhi, R.id.relativeLayout10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shezhi:
                Intent intent=new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_head:
                Intent intent1=new Intent(getContext(), ChangeUserActivity.class);
                EventBus.getDefault().postSticky(user1);
                System.out.println(user1.msg);
                startActivity(intent1);
                break;
        }
    }
}

