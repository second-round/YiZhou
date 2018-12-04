package com.example.mvp.mvp2.model;

import android.os.Handler;
import android.os.Message;

import com.example.mvp.mvp2.bean.RegBean;
import com.example.mvp.mvp2.callback.MyCallBack;
import com.example.mvp.mvp2.utils.HttpUtils;
import com.google.gson.Gson;

public class IModelImpl implements IModel {
    private MyCallBack callBack;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    RegBean regBean= (RegBean) msg.obj;
                    if (callBack!=null){
                        callBack.sedata(regBean);
                    }
                default:
                    break;

            }
        }
    };

    @Override
    public void requestData(final String url, final String params, MyCallBack callBack) {
        this.callBack=callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String s = HttpUtils.get(url);
                    RegBean regBean = new Gson().fromJson(s, RegBean.class);
                    handler.sendMessage(handler.obtainMessage(0,regBean));
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(0,"错误"));
                }
            }
        }).start();


    }
}
