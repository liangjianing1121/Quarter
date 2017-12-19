package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.quarter.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.SearchFriend;

/**
 * Created by DELL on 2017/12/18.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private  Context context;
    private  List<SearchFriend.DataBean> data;

    public SearchAdapter(Context context, List<SearchFriend.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.search_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iv.setImageURI(data.get(position).icon);
        holder.tv_name.setText(data.get(position).nickname);

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
