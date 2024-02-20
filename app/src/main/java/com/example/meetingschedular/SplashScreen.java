package com.example.meetingschedular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.meetingschedular.Utils.StatusBar;

public class SplashScreen extends AppCompatActivity {
    ImageView img;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StatusBar.iconStatusBar(this, R.color.white);
        img = findViewById(R.id.img);
        tv = findViewById(R.id.tv);
        img.animate().translationY(-1600).setDuration(1200).setStartDelay(2700);
        tv.animate().translationY(1800).setDuration(1200).setStartDelay(2700);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },4000);


    }
}