package com.lubin.lubin.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lubin.lubin.R;
import com.lubin.widget.inputbox.InputBoxLayout;

public class InputBoxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_box);
        InputBoxLayout layout=findViewById(R.id.box);
        layout.setOnBoxListener(new InputBoxLayout.OnBoxListener() {
            @Override
            public void onIsOrNotComplete(boolean isComplete, String text) {
                Toast.makeText(InputBoxActivity.this, "全部："+isComplete+" "+text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
