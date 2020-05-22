package com.karthick.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    int splashScreenTimeout = 1500;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.app_logo);

        imageView.animate().alpha(1).setDuration(1200).start();

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            //THIS WILL STOP GOING BACK TO WELCOME SCREEN, WHEN BACK BUTTON IS PRESSED
            finish();
        }, splashScreenTimeout);
    }
}
