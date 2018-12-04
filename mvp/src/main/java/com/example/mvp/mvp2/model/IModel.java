package com.example.mvp.mvp2.model;

import com.example.mvp.mvp2.callback.MyCallBack;

public interface IModel {
    void requestData(String url, String params, MyCallBack callBack);
}
