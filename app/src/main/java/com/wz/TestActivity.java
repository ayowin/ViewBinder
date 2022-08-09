package com.wz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wz.binder.Bind;
import com.wz.binder.ViewBinder;

public class TestActivity extends AppCompatActivity {

    @Bind
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ViewBinder.bind(this,R.id.class);

        /* 事件初始化 */
        initEvent();
    }

    private void initEvent(){
        textView.setText("test...");
    }
}