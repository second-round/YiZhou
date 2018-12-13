package com.bawei.mymvp.recycler.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.bawei.mymvp.R;
import com.bawei.mymvp.recycler.adapter.LinearAdapter;
import com.bawei.mymvp.recycler.bean.User;

public class RecycleLinearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_linear);
        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        //写一个布局管理器，写一个线性管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置方向，这里用垂直方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        LinearAdapter linearAdapter = new LinearAdapter();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("张" + i);
            linearAdapter.addItem(user);
        }

        //设置Adapter
        recyclerView.setAdapter(linearAdapter);
        //设置分隔线（系统提供）
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        //添加自定义分割线
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.recycler_divider_horizontal));
        recyclerView.addItemDecoration(divider);




















        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
