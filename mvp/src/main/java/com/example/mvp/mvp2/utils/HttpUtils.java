package com.example.mvp.mvp2.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    public static String get(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(5000);
        InputStream inputStream = urlConnection.getInputStream();
        String s=getInputStr(inputStream);
        return s;
    }

    private static String getInputStr(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer stringBuffer = new StringBuffer();
        String str=null;
        while ((str=reader.readLine())!=null){
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }
}