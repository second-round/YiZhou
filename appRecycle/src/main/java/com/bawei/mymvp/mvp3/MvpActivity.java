package com.bawei.mymvp.mvp3;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.mymvp.Apis;
import com.bawei.mymvp.Constants;
import com.bawei.mymvp.R;
import com.bawei.mymvp.mvp3.bean.GetUserInfoBean;
import com.bawei.mymvp.mvp3.bean.LoginBean;
import com.bawei.mymvp.mvp3.bean.UpLoadImgBean;
import com.bawei.mymvp.mvp3.presenter.IPresenterImpl;
import com.bawei.mymvp.mvp3.utils.SpUtils;
import com.bawei.mymvp.mvp3.view.IView;

import java.util.HashMap;
import java.util.Map;

public class MvpActivity extends AppCompatActivity implements IView, View.OnClickListener {

    private IPresenterImpl mPresenter;
    private Button mButtonLogin, mButtonUpLoadImg, mButtonGetUserInfo;
    private EditText mEtName, mEtPw;

    /**
     *  因为这个页面有三个接口，所以根据type分类，让接下来的方法，知道该调用哪个接口
     */
    private final int TYPE_LOGIN = 0;
    private final int TYPE_UPLOAD_IMG = TYPE_LOGIN + 1;
    private final int TYPE_GET_USER_INFO = TYPE_UPLOAD_IMG + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp2);
        mPresenter = new IPresenterImpl(this);
        initView();
    }

    private void initView() {
        mButtonLogin = findViewById(R.id.button_main_login);
        mButtonUpLoadImg = findViewById(R.id.button_main_up_load_img);
        mButtonGetUserInfo = findViewById(R.id.button_main_get_user_info);

        mEtName = findViewById(R.id.et_main_name);
        mEtPw = findViewById(R.id.et_main_pw);

        mButtonLogin.setOnClickListener(this);
        mButtonUpLoadImg.setOnClickListener(this);
        mButtonGetUserInfo.setOnClickListener(this);

        mEtName.setText("13800138000");
        mEtPw.setText("123456");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_login:
                checkPermission(TYPE_LOGIN);
                break;
            case R.id.button_main_up_load_img:
                checkPermission(TYPE_UPLOAD_IMG);
                break;
            case R.id.button_main_get_user_info:
                checkPermission(TYPE_GET_USER_INFO);
                break;
            default:
                break;
        }
    }


    private void checkPermission(int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, type);
            } else {
                startRequest(type);
            }
        } else {
            startRequest(type);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startRequest(requestCode);
        }
    }

    private void startRequest(int type) {
        switch (type) {
            case TYPE_LOGIN:
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mEtName.getText().toString());
                params.put("password", mEtPw.getText().toString());
                mPresenter.startRequest(Apis.URL_LOGIN_POST, params, LoginBean.class);
                break;
            case TYPE_UPLOAD_IMG:
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" + "图片1.png";
                Map<String , String > map = new HashMap<>();
                map.put("uid", SpUtils.getString(this, Constants.SP_KEY_UID, ""));
                map.put(Constants.MAP_KEY_UP_LOAD_IMG, path);

                mPresenter.startUpLoadImg(Apis.URL_UPLOAD_IMG, map, UpLoadImgBean.class);
                break;
            case TYPE_GET_USER_INFO:
                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("uid", SpUtils.getString(this, Constants.SP_KEY_UID, ""));
                mPresenter.startRequest(Apis.URL_GET_USER_INFO, paramsMap, GetUserInfoBean.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void showResponseData(Object data) {
        if(data instanceof LoginBean) {
            LoginBean loginBean = (LoginBean) data;
            Log.i("dj", "uid is " + loginBean.getData().getUid());

            SpUtils.save(this, Constants.SP_KEY_UID, loginBean.getData().getUid());
        }else if(data instanceof UpLoadImgBean){
            UpLoadImgBean upLoadImgBean = (UpLoadImgBean) data;
            Toast.makeText(MvpActivity.this, upLoadImgBean.getMsg(), Toast.LENGTH_SHORT).show();
        }else if(data instanceof GetUserInfoBean){
            GetUserInfoBean getUserInfoBean = (GetUserInfoBean) data;
            Log.i("dj", "icon is " + getUserInfoBean.getData().getIcon());
        }
    }
}
