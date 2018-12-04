package com.example.mvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp.presemter.LoginPresenter;
import com.example.mvp.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {
    private EditText mEtName, mEtPw;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        mLoginPresenter=new LoginPresenter(this);
    }

    private void initView() {
        mEtName=findViewById(R.id.et_name);
        mEtPw=findViewById(R.id.et_pw);

        mEtPw.invalidate();
        findViewById(R.id.button_login).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.button_login:
                User user=new User(mEtName.getText().toString(),mEtPw.getText().toString());
                mLoginPresenter.submit(user);
                break;
            default:
                break;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }


    @Override
    public void success(Object data) {
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}