package com.ajanovski.hairsalonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.ajanovski.hairsalonapp.utils.AppHolder;

import ajanovski.hairsalonapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        User user1 = new User("Trifun Ajanovski", "tifun", "android");
        User user2 = new User("Elizabeta Minova", "elizabeta", "beti");
        User user3 = new User("Nikolina Nikolova", "nikolina", "niki");

        AppHolder.users.add(user1);
        AppHolder.users.add(user2);
        AppHolder.users.add(user3);


        new CountDownTimer(4000, 1000) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}