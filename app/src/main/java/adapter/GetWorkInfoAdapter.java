package adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.dell.quarter.R;
import com.example.dell.quarter.UserVideoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.GetWorkInfo;
import bean.User;
import bean.UserVideos;
import de.greenrobot.event.EventBus;

/**
 * Created by DELL on 2017/12/15.
 */

public class GetWorkInfoAdapter extends RecyclerView.Adapter<GetWorkInfoAdapter.ViewHolder> {
    private Context context;
    private GetWorkInfo.DataBean data;

    private ObjectAnimator rotation;
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator fanimator;
    private ObjectAnimator fanimator1;
    private ObjectAnimator fanimator2;
    private ObjectAnimator fanimator3;




    public GetWorkInfoAdapter(Context context, GetWorkInfo.DataBean data) {
        this.context = context;
        this.data=data;
    }

    @Override
    public GetWorkInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.uservideo_item, null);
        GetWorkInfoAdapter.ViewHolder holder = new GetWorkInfoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.iv.setImageURI(data.user.icon);
        holder.tv_name.setText(data.user.nickname);


        View player = View.inflate(context, R.layout.simple_player_view_player, holder.player);
        String videoUrl = data.worksEntities.get(position).videoUrl;
        String replace = videoUrl.replace("https://www.zhaoapi.cn", "http://120.27.23.105");

        PlayerView playerView = new PlayerView((Activity) context, player)
                .setTitle(data.worksEntities.get(position).workDesc)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .setPlaySource(replace)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(context).load(data.worksEntities.get(position).cover).into(ivThumbnail);
                    }
                });
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
                Intent intent = new Intent(context, UserVideoActivity.class);
                context.startActivity(intent);
                EventBus.getDefault().postSticky(data.worksEntities.get(position));
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

    }



    @Override
    public int getItemCount() {
        return data.worksEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv_name;
        private final TextView tv_date;
        private RelativeLayout player;
        private final TextView tv1;
        private final ImageView iv_shutdown;
        private final TextView tv2;
        private final TextView tv3;

        private final ImageView iv_animation;
        private final LinearLayout iv_animation1;
        private final LinearLayout iv_animation2;
        private final LinearLayout iv_animation3;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            player = itemView.findViewById(R.id.player);
            iv_animation = itemView.findViewById(R.id.iv_animation);
            iv_animation1 = itemView.findViewById(R.id.iv_animation1);
            iv_animation2 = itemView.findViewById(R.id.iv_animation2);
            iv_animation3 = itemView.findViewById(R.id.iv_animation3);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            iv_shutdown = itemView.findViewById(R.id.iv_shutdown);

        }
    }
}