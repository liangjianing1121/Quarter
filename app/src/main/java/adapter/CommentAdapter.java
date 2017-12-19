package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.quarter.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bean.User;
import bean.VideoDetail;
import presenter.UserPresenter;
import view.UserView;

/**
 * Created by DELL on 2017/12/18.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>  {

    private Context context;
    private List<VideoDetail.DataBean.CommentsBean> data;
    private String icon1;

    public CommentAdapter(Context context, List<VideoDetail.DataBean.CommentsBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.comment_item, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_comment_name.setText(data.get(position).nickname);
        holder.tv_comment.setText(data.get(position).content);
        int uid = data.get(position).uid;




    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    public  class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_comment;
        private final TextView tv_comment_name;
        private final SimpleDraweeView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_comment = itemView.findViewById(R.id.tv_comment);
            tv_comment_name = itemView.findViewById(R.id.tv_comment_name);
            iv = itemView.findViewById(R.id.iv);


        }
    }
}
