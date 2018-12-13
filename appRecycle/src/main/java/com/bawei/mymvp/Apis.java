package com.bawei.mymvp;

/**
 * 所有请求url抽取出来
 */
public class Apis {
    public static final String URL_LOGIN_POST = "http://www.zhaoapi.cn/user/login";

    public static final String URL_LOGIN = "http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    public static final String URL_UPLOAD_IMG  = "http://www.zhaoapi.cn/file/upload";
    public static final String URL_GET_USER_INFO = "http://www.zhaoapi.cn/user/getUserInfo?uid=%s";
}
