package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Intent;
import android.oa.com.ua.pharmacy.R;
import android.os.Bundle;
import android.widget.ProgressBar;


public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Emulation resource utilization
        new Thread(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                try {
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                    for (int i = 0; i < 100; i += 10) {
                        progressBar.setProgress(i);
                        Thread.sleep(200);
                    }
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
