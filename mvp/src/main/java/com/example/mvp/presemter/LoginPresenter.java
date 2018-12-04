package com.example.mvp.presemter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.mvp.NetUtils;
import com.example.mvp.User;
import com.example.mvp.view.IView;

public class LoginPresenter {
    private IView mIView;

    public LoginPresenter(@NonNull IView mIView) {
        this.mIView = mIView;
    }

    public void submit(User user) {
        if (checkName(user.getName())&&checkPw(user.getPw())){
            boolean loginResult=NetUtils.loginApi(user);
            if (loginResult){
                mIView.success("");
            }else {
                mIView.fail("失败");
            }
        }
    }

    private boolean checkName(String name) {
        return !TextUtils.isEmpty(name);
    }

    public void detachView() {
        mIView=null;
    }


    private boolean checkPw(String pw) {
        return (!TextUtils.isEmpty(pw)&&pw.length()>=6);
    }


}
