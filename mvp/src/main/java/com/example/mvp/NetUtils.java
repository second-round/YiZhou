package com.example.mvp;

import android.os.SystemClock;

public class NetUtils {
    public static boolean loginApi(User user){
        SystemClock.sleep(2000);
        if (user.getName().equals("hyy")&&user.getPw().equals("123456")){
            return true;
        }
        return false;
    }
}