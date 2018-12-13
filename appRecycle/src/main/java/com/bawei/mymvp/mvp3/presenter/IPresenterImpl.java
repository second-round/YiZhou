package com.bawei.mymvp.mvp3.presenter;

import android.util.Log;

import com.bawei.mymvp.mvp3.callback.MyCallBack;
import com.bawei.mymvp.mvp3.model.IModelImpl;
import com.bawei.mymvp.mvp3.view.IView;

import java.util.Map;

/**
 * iPresenter实现接口类
 */
public class IPresenterImpl implements IPresenter {
    private IModelImpl model;
    private IView iView;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        model = new IModelImpl();
    }

    @Override
    public void startRequest(String url, Map<String, String> params, Class clazz) {
        model.requestData(url, params, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.showResponseData(data);
            }
        });
    }

    @Override
    public void startUpLoadImg(String url, Map<String, String> map, Class clazz) {
        model.upLoadImg(url, map, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.showResponseData(data);
            }
        });
    }

    public void onDetach() {
        if (model != null) {
            model = null;
        }
        if (iView != null) {
            iView = null;
        }
    }
}
