package com.bawei.mymvp.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mymvp.R;
import com.bawei.mymvp.recycler.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 继承RecyclerView.Adapter<LinearAdapter.ViewHolder>
 */
public class LinearAdapter extends RecyclerView.Adapter<LinearAdapter.ViewHolder> {
    private List<User> mDatas;

    public LinearAdapter() {
        this.mDatas = new ArrayList<>();
    }

    public void addItem(User user) {
        if (user != null) {
            mDatas.add(user);
        }
    }

    /**
     * 静态内部类 ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        //列出所有需要用到的控件
        public final TextView title;
        public final ImageView avatar;

        //写一个构造方法，找到所有的控件
        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_recycle_linear);
            avatar = v.findViewById(R.id.iv_recycle_linear);
        }
    }

    // 绑定ViewHolder,写入数据
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mDatas.get(position);
        holder.title.setText(user.getName());
    }

    //返回条目数
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //重写创建ViewHolder 把条目布局添加进来，返回viewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_linear, parent, false);
        return new ViewHolder(v);
    }
}
