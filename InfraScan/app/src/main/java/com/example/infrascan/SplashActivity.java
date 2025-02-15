package com.example.infrascan;// SplashActivity.java
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.infrascan.MainActivity;
import com.example.infrascan.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_splash);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        FirebaseApp.initializeApp(this);
        new Handler().postDelayed(() -> {
            if (currentUser != null) {
                // User is signed in, go to MainActivity
                startActivity(new Intent(SplashActivity.this, Auth.class));
            } else {
                // No user is signed in, show login page
                startActivity(new Intent(SplashActivity.this, Auth.class));
            }
            finish();
        }, SPLASH_DURATION);


    }
}