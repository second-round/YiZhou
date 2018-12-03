package com.example.yizhou;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yizhou.database.UserDao;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private UserDao dao;
    private SearchView searchView,findView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> list=new ArrayList<>();
        dao=new UserDao(SearchActivity.this);
        searchView=findViewById(R.id.search_view);
        findView=findViewById(R.id.find_view);
        list.add("考拉三周年人气热销榜");
        list.add("电动牙刷");
        list.add("尤妮佳");
        list.add("豆豆鞋");
        list.add("沐浴露");
        list.add("日东红茶");
        list.add("榴莲");
        list.add("电动牙刷");
        list.add("尤妮佳");
        list.add("雅诗兰黛");
        list.add("豆豆鞋");
        for (int i=0;i<list.size();i++){
            final TextAttr textView=new TextAttr(SearchActivity.this);
            textView.setText(list.get(i));
            findView.addView(textView);
            dao.add(list.get(i),"find");
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setTextColor(Color.RED);
                    Toast.makeText(SearchActivity.this,textView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        final List<String> search = dao.select("search");
        for (int i=0;i<search.size();i++){
            final TextView textView=new TextView(SearchActivity.this);
            textView.setText(search.get(i));
            searchView.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this,textView.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }
        final EditText editText=findViewById(R.id.editText);
        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                dao.add(text,"search");
                final TextView textView=new TextView(SearchActivity.this);
                textView.setText(text);
                searchView.addView(textView);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(SearchActivity.this,textView.getText().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.delete("search");
                searchView.removeAllViews();
            }
        });

    }
}
