package com.bawei.mymvp.recycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mymvp.R;
import com.bawei.mymvp.recycler.bean.User;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {
    private Context context;
    private List<User> mDatas;

    public StaggeredAdapter(Context context) {
        this.mDatas = new ArrayList<>();
        this.context = context;
    }
    public void addItem(User user) {
        if (user != null) {
            mDatas.add(user);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView avatar;
        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_recycle_staggered);
            avatar = v.findViewById(R.id.iv_recycle_staggered);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mDatas.get(position);
        holder.title.setText(user.getName());
        Glide.with(context).load(user.getAvatar()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle_staggered, parent, false);
        return new ViewHolder(view);
    }
}
