package com.bawei.mymvp.mvp3.presenter;

import java.util.Map;

/**
 * P层的接口
 * 里面简单封装了一个开始请求网络 开始做任务
 */
public interface IPresenter {
    void startRequest(String url, Map<String, String> params, Class clazz);

    void startUpLoadImg(String url, Map<String, String> map, Class clazz);
}
