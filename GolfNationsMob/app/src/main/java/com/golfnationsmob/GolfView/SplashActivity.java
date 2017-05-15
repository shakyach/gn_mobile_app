package com.golfnationsmob.GolfView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.golfnationsmob.R;

public class SplashActivity extends Activity {
    private static String TAG = SplashActivity.class.getName();
    private static long SLEEP_TIME = 1;    // Sleep for some time
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
      //  this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,     WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

        setContentView(R.layout.activity_splash);
       // this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.header);

         dialog=new ProgressDialog(SplashActivity.this,R.style.MyAlertDialogStyle);
        dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
        // dialog.setMessage("Loading...");

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
       /* params.x = 85;
        dialog.getWindow().setAttributes(params);*/
        dialog.setCancelable(false);
        // dialog.setInverseBackgroundForced(false);
        dialog.show();
        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            // Start main activity
            dialog.cancel();
            Intent intent = new Intent(SplashActivity.this, MainPage.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
