package com.dotdat.nightlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        handler.postDelayed(runnable, 1500);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreen.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    };
}
