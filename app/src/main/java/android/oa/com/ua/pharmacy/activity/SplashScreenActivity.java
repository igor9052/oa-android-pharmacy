package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.os.Bundle;


public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                try {
                    Thread.sleep(2000);
                    Intent intent = new Intent(SplashScreenActivity.this, CatalogActivity.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // close this activity
                    finish();
                }
            }
        }).start();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

}
