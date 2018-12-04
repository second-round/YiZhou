package com.example.mvp.mvp2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.R;
import com.example.mvp.mvp2.bean.RegBean;
import com.example.mvp.mvp2.presenter.IPresenterImpl;
import com.example.mvp.mvp2.view.IView;

import org.w3c.dom.Text;

public class MVPActivityTwo extends AppCompatActivity implements IView,View.OnClickListener {
    private IPresenterImpl presenter;
    private Button send_Btn;
    private TextView get_Text;
    private String mUrl="http://www.zhaoapi.cn/user/reg?mobile=18210302397&password=123456";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvptwo);
        presenter=new IPresenterImpl(this);
        initView();
    }

    private void initView() {
        send_Btn=findViewById(R.id.send_Btn);
        get_Text=findViewById(R.id.get_text);
        send_Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_Btn:
                checkPermission();
                break;
            default:
                break;
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET},100);
            }else {
                startRequest();
            }
        }else {
            startRequest();
        }
    }

    private void startRequest() {
        presenter.startRequest(mUrl,null);
    }

    @Override
    public void showResponseData(Object data) {
        RegBean regBean= (RegBean) data;
        get_Text.setText(String.valueOf(regBean.getData()));
        Toast.makeText(this,String.valueOf(regBean.getData()),Toast.LENGTH_SHORT).show();
    }
}