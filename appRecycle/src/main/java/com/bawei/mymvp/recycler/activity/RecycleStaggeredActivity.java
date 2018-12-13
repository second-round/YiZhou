package com.bawei.mymvp.recycler.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.bawei.mymvp.R;
import com.bawei.mymvp.recycler.adapter.StaggeredAdapter;
import com.bawei.mymvp.recycler.bean.User;
import com.bawei.mymvp.recycler.view.DividerGridItemDecoration;

public class RecycleStaggeredActivity extends AppCompatActivity {
    private final int mSpanCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_staggered);

        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        //使用瀑布流布局,第一个参数 spanCount 一行几个,第二个参数 orentation 排列方向
        StaggeredGridLayoutManager recyclerViewLayoutManager =
                new StaggeredGridLayoutManager(mSpanCount, StaggeredGridLayoutManager.VERTICAL);

        //设置布局管理器
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        StaggeredAdapter staggeredAdapter = new StaggeredAdapter(this);

        int[] avatarArray = new int[]{
                R.drawable.timg, R.drawable.timg1, R.drawable.timg3};
        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("张" + i);
            user.setAvatar(avatarArray[i % avatarArray.length]);
            staggeredAdapter.addItem(user);
        }

        //设置Adapter
        recyclerView.setAdapter(staggeredAdapter);
        //设置分隔线
        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        recyclerView.addItemDecoration(dividerGridItemDecoration);

        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
