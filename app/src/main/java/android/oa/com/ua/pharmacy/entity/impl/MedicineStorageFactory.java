package android.oa.com.ua.pharmacy.entity.impl;

import android.content.Context;
import android.oa.com.ua.pharmacy.R;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.oa.com.ua.pharmacy.util.Settings;
import android.util.Log;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Kuzmenko on 20.02.2015.
 */
public class MedicineStorageFactory {

    public static final String TEST_MEDICINE_STORAGE = "default";
    public static final String TEST_CATALOG = "test_catalog";
    public static final String LOCAL_JSON_DATA = "local_json";
    private static final String TAG = "MedicineStorageFactory";
    private static int[] mThumbIds = {R.drawable.aromat, R.drawable.cosmet, R.drawable.gomeo, R.drawable.med, R.drawable.mom_child, R.drawable.preparaty};
    private static String[] textArray = {"Ароматерапия", "Косметика", "Гомеопатия", "Изделия медицинского назначения", "Товары для детей и мам", "Лекарственные препараты"};
    private static String testDescription = "Your doctor may have suggested this medication for conditions other than those listed in these drug information articles";

    public static IMedicineStorage makeStorage(Context context, String type) {
        switch (type) {
            case TEST_MEDICINE_STORAGE:
                return createTestMedicineStorage();
            case TEST_CATALOG:
                return createCatalog();
            case LOCAL_JSON_DATA:
                return getDataFromJson(context);
            default:
                return createTestMedicineStorage();
        }
    }

    private static IMedicineStorage getDataFromJson(Context context) {
        FileInputStream in;
        StringBuilder stringBuilder = new StringBuilder();
        byte[] buffer = new byte[1024];
        int n;
        try {
            in = context.openFileInput(Settings.DATA_FILE);
            while ((n = in.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, stringBuilder.toString());
        Gson gson = new Gson();
        return gson.fromJson(stringBuilder.toString(), MedicineStorage.class);
    }

    private static IMedicineStorage createCatalog() {
        List<MedicineCategory> categoryList = new ArrayList<>();
        for (int i = 0; i < mThumbIds.length; i++) {
            categoryList.add(new MedicineCategory(i + 1, textArray[i], "", mThumbIds[i], createProductList(textArray[i])));
        }
        return new MedicineStorage(categoryList);
    }

    private static List<MedicineProduct> createProductList(String categoryName) {
        List<MedicineProduct> productList = new ArrayList<>();
        for (int i = 0; i < mThumbIds.length; i++) {
            productList.add(new MedicineProduct(i + 1, textArray[i], testDescription, "", mThumbIds[i], categoryName));
        }
        return productList;

    }


    private static IMedicineStorage createTestMedicineStorage() {

        List<MedicineCategory> categoryList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            List<MedicineProduct> itemList = new ArrayList<>();
            for (int k = 1; k < 20; k++) {
                itemList.add(new MedicineProduct(k, "Item " + (i * k), "", testDescription + (i * k), 1, "Category name " + i));
            }
            categoryList.add(new MedicineCategory(i, "Category name " + i, "", 1, itemList));
        }
        return new MedicineStorage(categoryList);
    }


}
