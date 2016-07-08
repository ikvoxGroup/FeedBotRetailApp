
package com.feedbotretailapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import LoginSessionManagement.UserSessionManager;


/**
 * Splash Activity is the start-up activity that starts, various app
 * initialization operations are performed and user login details
 */
public class SplashActivity extends Activity {
    private final static String LOG_TAG = SplashActivity.class.getSimpleName();

    // User Session Manager Class
    UserSessionManager session;

    ImageView splashImage;
    private ProgressBar spinner;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");

        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_splash);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        splashImage = (ImageView) findViewById(R.id.splashImage);

        // Session class instance
        session = new UserSessionManager(getApplicationContext());
        spinner.setVisibility(View.VISIBLE);
        if (session.isUserLoggedIn()) {
           // Toast.makeText(SplashActivity.this, "Welcome to IkVox dashboard", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SplashActivity.this, "Please login", Toast.LENGTH_SHORT).show();
        }

        /*Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();*/

        mHandler.postDelayed(new Runnable() {
            public void run() {
                doStuff();
            }
        }, 4000);
    }

    // Check user login
    private void doStuff() {

        // If User is not logged in , This will redirect user to LoginActivity.
        if (session.isUserLoggedIn()) {
            spinner.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(SplashActivity.this, FeedbackActivity.class);
            startActivity(intent);
            finish();

        } else {
            spinner.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing.
        //for disable back button
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
