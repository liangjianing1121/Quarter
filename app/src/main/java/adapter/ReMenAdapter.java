package adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Presentation;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.sax.StartElementListener;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.dell.quarter.HomeActivity;
import com.example.dell.quarter.R;
import com.example.dell.quarter.UserVideoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import bean.AddFavorite;
import bean.Praise;
import bean.UserVideos;
import bean.Videos;
import de.greenrobot.event.EventBus;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.ReMenFragment;
import presenter.PraisePresenter;
import view.PraiseView;

/**
 * Created by DELL on 2017/12/2.
 */

public class ReMenAdapter  extends RecyclerView.Adapter<ReMenAdapter.ViewHolder> implements PraiseView {
    private Activity context;
    private List<Videos.DataBean> data;

    private ObjectAnimator rotation;
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator fanimator;
    private ObjectAnimator fanimator1;
    private ObjectAnimator fanimator2;
    private ObjectAnimator fanimator3;
    private PraisePresenter praisePresenter;

    public ReMenAdapter(Activity context, List<Videos.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.remenitem, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        praisePresenter = new PraisePresenter(this);
        holder.setIsRecyclable(false);

        holder.iv.setImageURI(data.get(position).user.icon);
        holder.tv_date.setText(data.get(position).createTime);
        String nickname = (String) data.get(position).user.nickname;
        holder.tv_name.setText(nickname);

        View player = View.inflate(context, R.layout.simple_player_view_player, holder.player);
        String videoUrl = data.get(position).videoUrl;
        String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");

        PlayerView playerView = new PlayerView(context,player)
                .setTitle(data.get(position).workDesc)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .setPlaySource(replace)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(context).load(data.get(position).cover).into(ivThumbnail);
                }
                })
               ;
        //隐藏返回键，true 隐藏，false 为显示
        playerView.hideBack(true);
        //隐藏菜单键，true 隐藏，false 为显示
        playerView.hideMenu(true);
        //隐藏分辨率按钮，true 隐藏，false 为显示
        playerView.hideSteam(true);
        //隐藏旋转按钮，true 隐藏，false 为显示
        playerView.hideRotation(true);
        //隐藏全屏按钮，true 隐藏，false 为显示
        playerView.hideFullscreen(true);
        //隐藏中间播放按钮,ture 为隐藏，false 为不做隐藏处理，但不是显示
        playerView.hideCenterPlayer(false);
        //隐藏全屏按钮，true 隐藏，false 为显示
        playerView.hideFullscreen(true);
        //设置是否禁止双击
        playerView.setForbidDoulbeUp(true);
        //隐藏全屏按钮，true 隐藏，false 为显示
        playerView.hideFullscreen(true);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UserVideoActivity.class);
                context.startActivity(intent);
                EventBus.getDefault().postSticky(data.get(position));
            }
        });


        holder.iv_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ObjectAnimator animator0 = ObjectAnimator.ofFloat(holder.iv_shutdown, "rotation", 0, -180);

                animator1 = ObjectAnimator.ofFloat(holder.iv_animation1, "translationX", 0, -330);
                animator2 = ObjectAnimator.ofFloat(holder.iv_animation2, "translationX", 0, -220);
                animator3 = ObjectAnimator.ofFloat(holder.iv_animation3, "translationX", 0, -110);

                holder.iv_shutdown.setVisibility(View.VISIBLE);
                holder.iv_animation.setVisibility(View.GONE);
                AnimatorSet set = new AnimatorSet();
                set.play(animator0).with(animator1).with(animator2).with(animator3);
                set.setDuration(500);
                set.start();
                holder.tv1.setVisibility(View.VISIBLE);
                holder.tv2.setVisibility(View.VISIBLE);
                holder.tv3.setVisibility(View.VISIBLE);
            }
        });
        holder.iv_shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.iv_shutdown.setVisibility(View.GONE);
                holder.iv_animation.setVisibility(View.VISIBLE);

                //伸出的动画
                final ObjectAnimator animator = ObjectAnimator.ofFloat(holder.iv_animation, "rotation", 0, 180);

                //缩回的动画

                fanimator = ObjectAnimator.ofFloat(holder.iv_animation, "rotation", 0, -180);
                fanimator1 = ObjectAnimator.ofFloat(holder.iv_animation3, "translationX", -110, 0);
                fanimator2 = ObjectAnimator.ofFloat(holder.iv_animation2, "translationX", -220, 0);
                fanimator3 = ObjectAnimator.ofFloat(holder.iv_animation1, "translationX", -330, 0);

                AnimatorSet set = new AnimatorSet();
                set.play(animator).with(fanimator1).with(fanimator2).with(fanimator3);
                set.setDuration(500);
                set.start();
                holder.tv1.setVisibility(View.GONE);
                holder.tv2.setVisibility(View.GONE);
                holder.tv3.setVisibility(View.GONE);

            }
        });




        holder.iv_xihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences uid = context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                praisePresenter.getPraise(uid1+"",data.get(position).wid+"");
                holder.iv_xihuan.setImageResource(R.drawable.hongxin);
                holder.tv_xihuan.setText(data.get(position).praiseNum+"");
            }
        });

        holder.iv_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences uid = context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                praisePresenter.addFavorite(uid1+"",data.get(position).wid+"");
                //holder.iv_shoucang.setImageResource(R.drawable.shoucanghuang);
                holder.iv_shoucang.setVisibility(View.GONE);
                holder.iv_shoucang1.setVisibility(View.VISIBLE);
                holder.tv_shoucang.setText(data.get(position).favoriteNum+"");


            }
        });

        holder.iv_shoucang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences uid = context.getSharedPreferences("uid", Context.MODE_PRIVATE);
                int uid1 = uid.getInt("uid", 0);
                praisePresenter.cancelFavorite(uid1+"",data.get(position).wid+"");

                holder.iv_shoucang.setVisibility(View.VISIBLE);
                holder.iv_shoucang1.setVisibility(View.GONE);
                holder.tv_shoucang.setText(data.get(position).favoriteNum+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * BaseView返回的方法
     * @param o
     */

    @Override
    public void RequestSuccess(Object o) {

    }

    @Override
    public void RequestFailure(Object o) {

    }

    @Override
    public void Failure(Object o) {

    }

    /**
     * 喜欢返回的方法
     * @param praise
     */

    @Override
    public void getPraiseSuccess(Praise praise) {
        Toast.makeText(context,praise.msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void getPariesFailure(Praise praise) {
        Toast.makeText(context,praise.msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 收藏返回的方法
     * @param addFavorite
     */

    @Override
    public void addFavoriteSuccess(AddFavorite addFavorite) {
        Toast.makeText(context,addFavorite.msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addFavoriteFailure(AddFavorite addFavorite) {
        Toast.makeText(context,addFavorite.msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelFavoriteSuccess(AddFavorite addFavorite) {
        Toast.makeText(context,addFavorite.msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelFavoriteFailure(AddFavorite addFavorite) {
        Toast.makeText(context,addFavorite.msg, Toast.LENGTH_SHORT).show();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv_name;
        private final TextView tv_date;
        private RelativeLayout player;
        private final TextView tv1;
        private final ImageView iv_shutdown;
        private final  TextView tv2;
        private final  TextView tv3;

        private final ImageView iv_animation;
        private final LinearLayout iv_animation1;
        private final LinearLayout iv_animation2;
        private final LinearLayout iv_animation3;
        private final ImageView iv_xihuan;
        private final TextView tv_xihuan;
        private final ImageView iv_shoucang;
        private final TextView tv_shoucang;
        private final ImageView iv_shoucang1;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            player=itemView.findViewById(R.id.player);
            iv_animation = itemView.findViewById(R.id.iv_animation);
            iv_animation1 = itemView.findViewById(R.id.iv_animation1);
            iv_animation2 = itemView.findViewById(R.id.iv_animation2);
            iv_animation3 = itemView.findViewById(R.id.iv_animation3);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            iv_shutdown=itemView.findViewById(R.id.iv_shutdown);
            iv_xihuan = itemView.findViewById(R.id.iv_xihuan);
            tv_xihuan = itemView.findViewById(R.id.tv_xihuan);
            iv_shoucang = itemView.findViewById(R.id.iv_shoucang);
            tv_shoucang = itemView.findViewById(R.id.tv_shoucang);
            iv_shoucang1 = itemView.findViewById(R.id.iv_shoucang1);

        }
    }
    public void refreshData(List<Videos.DataBean> list){
        if(data != null){
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void loadmoreData(List<Videos.DataBean> list){
        if(data != null){
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

}
