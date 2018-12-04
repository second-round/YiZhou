package com.example.mvp.mvp2.presenter;

import com.example.mvp.mvp2.callback.MyCallBack;
import com.example.mvp.mvp2.model.IModelImpl;
import com.example.mvp.mvp2.view.IView;

public class IPresenterImpl implements IPresenter {
    private IModelImpl model;
    private IView iView;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        model=new IModelImpl();
    }

    @Override
    public void startRequest(String url, String params) {
        model.requestData(url, params, new MyCallBack() {
            @Override
            public void sedata(Object data) {
                iView.showResponseData(data);
            }
        });
    }

    public void onDetach(){
        if (model!=null){
            model=null;
        }
        if (iView!=null){
            iView=null;
        }
    }

}
