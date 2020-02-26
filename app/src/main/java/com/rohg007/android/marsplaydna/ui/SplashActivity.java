package com.rohg007.android.marsplaydna.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Space;

import com.rohg007.android.marsplaydna.MainActivity;
import com.rohg007.android.marsplaydna.R;
import com.rohg007.android.marsplaydna.viewmodels.DocViewModel;

public class SplashActivity extends AppCompatActivity {

    ImageView splashBranding;
    private static final int SPLASH_TIMEOUT = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashBranding = findViewById(R.id.splash_branding);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            },SPLASH_TIMEOUT);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadAnimationOnBranding();
    }

    private void loadAnimationOnBranding(){
        Animation zoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
        splashBranding.startAnimation(zoomIn);
    }
}
