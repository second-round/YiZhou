package com.bawei.mymvp.recycler.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bawei.mymvp.R;

public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        initView();
    }

    private void initView() {
        findViewById(R.id.button_recycler_linear).setOnClickListener(this);
        findViewById(R.id.button_recycler_grid).setOnClickListener(this);
        findViewById(R.id.button_recycler_flow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.button_recycler_linear:
                intent.setClass(this,RecycleLinearActivity.class);
                break;
            case R.id.button_recycler_grid:
                intent.setClass(this,RecycleGridActivity.class);
                break;
            case R.id.button_recycler_flow:
                intent.setClass(this,RecycleStaggeredActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
    }
}
