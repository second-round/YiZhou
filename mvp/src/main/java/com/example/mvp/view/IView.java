package com.example.mvp.view;

public interface IView<T> {
    void success(T data);
    void fail(String msg);

}
