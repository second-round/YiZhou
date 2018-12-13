package com.bawei.mymvp.mvp3.model;

import com.bawei.mymvp.okHttp.ICallBack;
import com.bawei.mymvp.okHttp.OkHttpUtils;
import com.bawei.mymvp.mvp3.callback.MyCallBack;

import java.util.Map;

/**
 * IModel接口实现类
 * 执行网络请求，对返回的数据进行处理
 */
public class IModelImpl implements IModel {

    /**
     * 处理请求数据
     * @param url 请求的url地址
     * @param params 如果为post请求，则传入post参数
     * @param callBack 回调
     */
    @Override
    public void requestData(final String url, Map<String, String> params, final Class clazz, final MyCallBack callBack) {
//        OkHttpUtils.getInstance().getEnqueue(url, new ICallBack() {
//            @Override
//            public void success(Object obj) {
//                callBack.setData(obj);
//            }
//
//            @Override
//            public void failed(Exception e) {
//                callBack.setData(e.getMessage());
//            }
//        }, clazz);


        OkHttpUtils.getInstance().postEnqueue(url, params, clazz, new ICallBack() {
            @Override
            public void success(Object obj) {
                callBack.setData(obj);
            }

            @Override
            public void failed(Exception e) {
                callBack.setData(e.getMessage());
            }
        });
    }

    @Override
    public void upLoadImg(String url, Map<String , String > map , Class clazz, final MyCallBack callBack) {
        OkHttpUtils.getInstance().uploadImage(url, map, clazz, new ICallBack() {
            @Override
            public void success(Object obj) {
                callBack.setData(obj);
            }

            @Override
            public void failed(Exception e) {
                callBack.setData(e.getMessage());
            }
        });
    }
}
