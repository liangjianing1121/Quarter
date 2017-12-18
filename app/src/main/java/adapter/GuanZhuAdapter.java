package adapter;

import android.content.Context;
import android.content.Intent;
import android.service.notification.ConditionProviderService;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.quarter.GetFollowUser;
import com.example.dell.quarter.GetWorkInfoActivity;
import com.example.dell.quarter.R;
import com.example.dell.quarter.UserVideoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import presenter.ChangUserPresenter;

/**
 * Created by DELL on 2017/12/14.
 */

public class GuanZhuAdapter extends RecyclerView.Adapter<GuanZhuAdapter.ViewHolder>{
    private Context context;
    private List<GetFollowUser.DataBean> data;

    public GuanZhuAdapter(Context context, List<GetFollowUser.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.guanzhu_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.iv_head.setImageURI(data.get(position).icon);
        holder.tv_name.setText(data.get(position).nickname);
        holder.tv_date.setText(data.get(position).createtime);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, GetWorkInfoActivity.class);
                intent.putExtra("uid",data.get(position).uid+"");
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;
        private final SimpleDraweeView iv_head;
        private final TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            iv_head = itemView.findViewById(R.id.iv_head);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
