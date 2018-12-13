package com.bawei.mymvp.mvp3.view;

/**
 * 抽取View实现接口
 */
public interface IView<T> {
    /**
     * 请求成功返回的数据
     */

    void showResponseData(T data);
}
