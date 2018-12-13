package com.bawei.mymvp.mvp3.callback;

/**
 * 接口保存Model层处理网络请求的值
 * 声明泛型为T
 * 通过对类型Object的引用来实现参数的“任意化”，“任意化”带来的缺点是要做显式的强制类型转换
 * 因为“任意化”，所以在编译的时候不会报错，只有在执行的时候直接崩溃
 *
 * @param <T>
 */
public interface MyCallBack<T> {
    void setData(T data);
}
