package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.quarter.R;

/**
 * Created by DELL on 2017/11/30.
 */

public class RvAdapter2 extends RecyclerView.Adapter<RvAdapter2.ViewHolder>  {

    private Context context;
    private String[] img;

    public RvAdapter2(Context context, String[] img) {
        this.context = context;
        this.img = img;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.zirvitem, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(img.length==1){
            holder.iv.setVisibility(View.VISIBLE);
            Glide.with(context).load(img[position]).into(holder.iv);
        }else{
            holder.iv2.setVisibility(View.VISIBLE);
            Glide.with(context).load(img[position]).into(holder.iv2);
        }


    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv;
        private final ImageView iv2;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv111);
            iv2 = itemView.findViewById(R.id.iv222);
        }
    }
}
