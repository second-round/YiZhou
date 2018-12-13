package com.bawei.mymvp.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.mymvp.R;
import com.bawei.mymvp.recycler.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 网格布局，跟线性布局几乎一样，除了GridAdapter.ViewHolder
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private List<User> mDatas;

    public GridAdapter() {
        this.mDatas = new ArrayList<>();
    }

    public void addItem(User user) {
        if (user != null) {
            mDatas.add(user);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView avatar;
        public final LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.tv_recycle_grid);
            avatar = v.findViewById(R.id.iv_recycle_grid);
            linearLayout = v.findViewById(R.id.ll_recycle_grid);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        User user = mDatas.get(position);
        holder.title.setText(user.getName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dj", "click linearLayout " + position);
                if(mClick != null){
                    mClick.OnClick(position);
                }
            }
        });

        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mClick != null){
                    mClick.OnLongClick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_grid, parent, false);
        return new ViewHolder(v);
    }

    /**
     * 增加数据，传入添加的位置和数据
     */
    public void addData(int position, User user) {
        mDatas.add(position, user);
        //必须使用notifyItemInserted 才能加载添加动画
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mDatas.size());
    }

    /**
     * 移除数据
     */
    public void removeData(int position) {
        mDatas.remove(position);
        //必须使用notifyItemRemoved 才能加载移除动画
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDatas.size());
    }


    Click mClick;

    public void setClickListener(Click click){
        mClick = click;
    }

    public interface Click{
        void OnClick(int position);
        void OnLongClick(int position);
    }
}