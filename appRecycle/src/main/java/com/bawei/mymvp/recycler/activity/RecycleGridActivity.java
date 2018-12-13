package com.bawei.mymvp.recycler.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.bawei.mymvp.R;
import com.bawei.mymvp.recycler.adapter.GridAdapter;
import com.bawei.mymvp.recycler.bean.User;
import com.bawei.mymvp.recycler.view.DividerGridItemDecoration;

public class RecycleGridActivity extends AppCompatActivity {
    //一行显示多少个
    private final int mSpanCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_grid);
        init();
    }

    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, mSpanCount);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置为垂直布局，这也是默认的

        final GridAdapter gridAdapter = new GridAdapter();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("张" + i);
            gridAdapter.addItem(user);
        }

        //设置Adapter
        recyclerView.setAdapter(gridAdapter);

        DividerGridItemDecoration dividerGridItemDecoration = new DividerGridItemDecoration(this);
        recyclerView.addItemDecoration(dividerGridItemDecoration);

        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        findViewById(R.id.button_recycler_grid_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setName("老戴");
                gridAdapter.addData(0, user);
            }
        });

        gridAdapter.setClickListener(new GridAdapter.Click() {
            @Override
            public void OnClick(int position) {
                Log.i("dj", "OnClick in activity " + position);
                gridAdapter.removeData(position);
            }

            @Override
            public void OnLongClick(int position) {
                Log.i("dj", "OnLongClick in activity " + position);
            }
        });
    }
}
