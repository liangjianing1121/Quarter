package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.example.dell.quarter.HomeActivity;
import com.example.dell.quarter.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.Videos;
import fragment.Fragment1;
import fragment.Fragment2;
import fragment.ReMenFragment;

/**
 * Created by DELL on 2017/12/2.
 */

public class ReMenAdapter  extends RecyclerView.Adapter<ReMenAdapter.ViewHolder> {
    private Activity context;
    private List<Videos.DataBean> list;

    public ReMenAdapter(Activity context, List<Videos.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.remenitem, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iv.setImageURI(list.get(position).user.icon);
        holder.tv_date.setText(list.get(position).createTime);
        String nickname = (String) list.get(position).user.nickname;
        holder.tv_name.setText(nickname);

        View player = View.inflate(context, R.layout.simple_player_view_player, null);

        PlayerView playerView = new PlayerView(context, player)
                .setTitle(list.get(position).workDesc)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(list.get(position).videoUrl)
                .startPlay();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv_name;
        private final TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}