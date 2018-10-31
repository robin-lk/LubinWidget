package com.lubin.lubin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lubin.lubin.view.InputBoxActivity;
import com.lubin.lubin.view.TabbarActivity;
import com.lubin.widget.dialog.MultiLevelBottomDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InputBoxActivity.class));
            }
        });
        findViewById(R.id.btn_tabbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TabbarActivity.class));
            }
        });
        findViewById(R.id.btn_multiLevel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiLevelBottomDialog.newInstance().show(getSupportFragmentManager(), this.getClass().getSimpleName());
            }
        });
    }
}
