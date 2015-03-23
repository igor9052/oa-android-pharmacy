package android.oa.com.ua.pharmacy.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineCategoryDAOImpl;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineProductDAOImpl;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineCategory;
import android.oa.com.ua.pharmacy.entity.impl.MedicineProduct;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.oa.com.ua.pharmacy.util.Settings;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SplashScreenActivity extends Activity {

    private static GetPackageTask getPackageTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (checkInitPackage()) {
            progressBar.setProgress(100);
            Intent intent = new Intent(SplashScreenActivity.this, CatalogActivity.class);
            startActivity(intent);
            return;
        }

        /*If GetPackage AsyncTask is active then getPackageTask != null, thus we just add progressBar
         * and activity to it. Otherwise the new AsyncTask is started. */
        if (getPackageTask == null) {
            getPackageTask = new GetPackageTask(this, progressBar);
            getPackageTask.execute(getApplication());
        } else {
            getPackageTask.setProgressBar(progressBar);
            getPackageTask.setActivity(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /*Check whether data file had been downloaded and stored in the Internal Memory*/
    private boolean checkInitPackage() {
        for (String item : fileList()) {
            if (Settings.DATA_FILE.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /*This AsyncTask downloads json file from internet and stores it to Internal Memory*/
    private static class GetPackageTask extends AsyncTask<Context, Integer, Void> {

        ProgressBar progressBar;
        Activity activity;

        private GetPackageTask(Activity activity, ProgressBar progressBar) {
            this.progressBar = progressBar;
            this.activity = activity;
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setProgress(0);
        }

        @Override
        protected Void doInBackground(Context... params) {
            Context context = params[0];
            int totalSize;
            int downloadedSize;
            byte[] buffer;
            int bufferLength;
            URL url;
            HttpURLConnection urlConnection;
            InputStream inputStream;

            FileOutputStream fos;

            try {
                url = new URL(Settings.Api.BASE_URL + Settings.DATA_FILE);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                fos = context.openFileOutput(Settings.DATA_FILE, MODE_PRIVATE);
                inputStream = urlConnection.getInputStream();

                totalSize = urlConnection.getContentLength();
                downloadedSize = 0;
                buffer = new byte[totalSize / 10];

                boolean isWiFi = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                        .getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;

                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    publishProgress(downloadedSize, totalSize + (totalSize / 10));
                    if (isWiFi) {
                        Thread.sleep(300);
                    }
                }
                fos.close();
                inputStream.close();
                initDbWithData(context);
                publishProgress(100, 100);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private void initDbWithData(Context context) {
            int currentCategoryId = 0;
            int currentProductId = 0;
            IMedicineStorage medicineStorage = MedicineStorageFactory.makeStorage(context, MedicineStorageFactory.LOCAL_JSON_DATA);
            MedicineCategoryDAOImpl medicineCategoryDAO = new MedicineCategoryDAOImpl(context);
            MedicineProductDAOImpl medicineProductDAO = new MedicineProductDAOImpl(context);
            for (IMedicineCategory category : ((MedicineStorage) medicineStorage).getCategories()) {
                currentCategoryId++;
                category.setId(currentCategoryId);
                medicineCategoryDAO.insert((MedicineCategory) category);
                for (MedicineProduct product : ((MedicineCategory) category).getItems()) {
                    currentProductId++;
                    product.setCategoryId(currentCategoryId);
                    product.setId(currentProductId);
                    medicineProductDAO.insert(product);
                }
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress((int) ((values[0] / (float) values[1]) * 100));
            Log.i("Downloading", String.valueOf(((int) ((values[0] / (float) values[1]) * 100))));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getPackageTask = null;
            Intent intent = new Intent(activity, CatalogActivity.class);
            activity.startActivity(intent);
        }
    }
}
