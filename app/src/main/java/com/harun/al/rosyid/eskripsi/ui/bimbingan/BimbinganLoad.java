package com.harun.al.rosyid.eskripsi.ui.bimbingan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.harun.al.rosyid.eskripsi.R;

public class BimbinganLoad extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), BimbinganReadActivity.class));
                finish();
            }
        },3000L);
    }
}