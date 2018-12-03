package com.example.yizhou.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private MyHelper helper;
    private SQLiteDatabase database;

    public UserDao(Context context) {
        helper=new MyHelper(context);
        database=helper.getReadableDatabase();
    }

    public void add(String name,String search) {
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("search",search);
        database.insert("users",null,values);
    }

    public List<String> select(String search) {
        List<String> list=new ArrayList<>();
        Cursor users = database.query("users", null, "search=?", new String[]{search}, null, null, null, null);
        while (users.moveToNext()){
            list.add(users.getString(users.getColumnIndex("name")));
        }
        return list;
    }

    public void delete(String search) {
        database.delete("users","search=?",new String[]{search});
    }
}