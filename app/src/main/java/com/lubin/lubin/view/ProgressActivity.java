package com.lubin.lubin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.lubin.lubin.R;
import com.lubin.widget.progress.HorizontalProgressBar;

public class ProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        HorizontalProgressBar bar=findViewById(R.id.progress_bar);
        bar.setmProgressSize(50);
        bar.runProgressAnim(5000);
    }
}
