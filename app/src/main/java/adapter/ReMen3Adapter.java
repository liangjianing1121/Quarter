package adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.sax.StartElementListener;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dell.quarter.R;
import com.example.dell.quarter.VideoActivity;

import java.util.ArrayList;
import java.util.List;

import bean.HotVideo;
import bean.Videos;
import utils.ClearCacheUtils;


/**
 * Created by DELL on 2017/12/7.
 */

public class ReMen3Adapter  extends RecyclerView.Adapter<ReMen3Adapter.ViewHolder> {
    private Context context;
    private  List<HotVideo.DataBean> data;
    private List<Integer> mHeights;

    public ReMen3Adapter(Context context, List<HotVideo.DataBean> data) {
        this.context = context;
        this.data = data;
        mHeights = new ArrayList<>();
        for(int i=0; i < data.size();i++){
            //随机的获取一个范围为200-600直接的高度
            mHeights.add((int)(200+Math.random()*400));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.remen3_item, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        try {
            layoutParams.height = mHeights.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.itemView.setLayoutParams(layoutParams);
        Glide.with(context).load(data.get(position).cover).centerCrop().into(holder.iv);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, VideoActivity.class);
                String videoUrl = data.get(position).videoUrl;
                intent.putExtra("videourl",videoUrl);
                String icon = data.get(position).user.icon;
                intent.putExtra("icon",icon);
                String workDesc = data.get(position).workDesc;
                intent.putExtra("desc",workDesc);
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

    public void refreshData(List<HotVideo.DataBean> list){
        if(data != null){
            data.clear();
            data.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void loadmoreData(List<HotVideo.DataBean> list){
        if(data != null){
            data.addAll(list);
            notifyDataSetChanged();
        }
    }
}
