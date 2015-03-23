package android.oa.com.ua.pharmacy.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineCategoryDAOImpl;
import android.oa.com.ua.pharmacy.db.dao.impl.MedicineProductDAOImpl;
import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineCategory;
import android.oa.com.ua.pharmacy.entity.impl.MedicineProduct;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorage;
import android.oa.com.ua.pharmacy.entity.impl.MedicineStorageFactory;
import android.oa.com.ua.pharmacy.util.Settings;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Igor Kuzmenko on 10.03.2015.
 * This service downloads content archive file from Internet
 */
public class GetPackage extends IntentService {


    public static final String TAG = GetPackage.class.getSimpleName();

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public GetPackage() {
        super(GetPackage.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Context context = getApplicationContext();
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
                Log.i(TAG, String.valueOf(downloadedSize));
                if (isWiFi) {
                    Thread.sleep(300);
                }
            }
            fos.close();
            inputStream.close();

            initDbWithData(context);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
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

    private void clearDB() {

    }
}
