package com.example.dell.controldevice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

/**
 * Created by dell on 10/01/2017.
 */
public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(getApplicationContext(),OptionActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}
