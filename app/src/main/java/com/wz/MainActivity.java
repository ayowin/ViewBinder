package com.wz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wz.binder.Bind;
import com.wz.binder.ViewBinder;

public class MainActivity extends AppCompatActivity {

    @Bind
    TextView textView;

    @Bind
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 自动绑定View */
        ViewBinder.bind(this,R.id.class);

        /* 事件初始化 */
        initEvent();
    }

    private void initEvent(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

}