package com.example.ekadarmaputra.sqlitetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by ekadarmaputra on 3/30/17.
 */

public class Splash extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {


// Showing splash screen with a timer

            @Override
            public void run() {
                Intent i = new Intent(Splash.this,Options.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
