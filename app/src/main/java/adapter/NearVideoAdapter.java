package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.quarter.R;
import com.example.dell.quarter.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import bean.HotVideo;
import bean.NearVideos;

/**
 * Created by DELL on 2017/12/19.
 */

public class NearVideoAdapter  extends RecyclerView.Adapter<NearVideoAdapter.ViewHolder> {
    private Context context;
    private List<NearVideos.DataBean>  data;
    private List<Integer> mHeights;

    public NearVideoAdapter(Context context, List<NearVideos.DataBean> data) {
        this.context = context;
        this.data = data;
        mHeights = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int) (300 + Math.random() * 400));
        }
    }

    @Override
    public NearVideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fujin_item, parent, false);
        NearVideoAdapter.ViewHolder holder = new NearVideoAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NearVideoAdapter.ViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        try {
            layoutParams.height = mHeights.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setLayoutParams(layoutParams);
        Glide.with(context).load(data.get(position).getCover()).centerCrop().into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoActivity.class);
                String videoUrl = data.get(position).getVideoUrl();
                intent.putExtra("videourl", videoUrl);
                String icon = data.get(position).getUser().getIcon();
                intent.putExtra("icon", icon);
                String workDesc = data.get(position).getWorkDesc();
                intent.putExtra("desc", workDesc);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    public void refreshData(List<NearVideos.DataBean> list) {
        if (data != null) {
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void loadmoreData(List<NearVideos.DataBean> list) {
        if (data != null) {
            data.addAll(list);
            notifyDataSetChanged();
        }
    }
}
