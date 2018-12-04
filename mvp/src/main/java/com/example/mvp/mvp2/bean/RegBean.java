package com.example.mvp.mvp2.bean;

public class RegBean<T> {
    private String msg;
    private String code;
    private T data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {

        return msg;
    }

    public String getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
}
