package com.bawei.mymvp.mvp3.bean;

public class UpLoadImgBean {
    private String code;
    private String msg;
    private LoginBean.Data data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LoginBean.Data getData() {
        return data;
    }

    public void setData(LoginBean.Data data) {
        this.data = data;
    }

    public class Data{
    }
}
