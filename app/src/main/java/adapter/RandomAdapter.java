package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.dell.quarter.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.RandomFriend;

/**
 * Created by DELL on 2017/12/18.
 */

public class RandomAdapter  extends  RecyclerView.Adapter<RandomAdapter.ViewHolder>{
    private Context context;
    private List<RandomFriend.DataBean> data;

    public RandomAdapter(Context context, List<RandomFriend.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.random_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String icon = (String) data.get(position).icon;
        holder.iv.setImageURI(String.valueOf(R.drawable.head));
        String nickname = (String) data.get(position).username;
        holder.tv_name.setText(nickname);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv;
        private final TextView tv_name;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
