package com.bawei.mymvp.mvp3.model;


import com.bawei.mymvp.mvp3.callback.MyCallBack;

import java.util.Map;

/**
 * Model接口
 */
public interface IModel {
    /**
     * '
     * 封装方法
     * 第一个参数：接口地址
     * 第二个参数：如果是post请求方式的话得传参数
     * 第三个参数：处理完保存到接口
     */
    void requestData(String url, Map<String, String> params, Class clazz, MyCallBack callBack);

    void upLoadImg(String url, Map<String, String> map, Class clazz, MyCallBack callBack);
}
