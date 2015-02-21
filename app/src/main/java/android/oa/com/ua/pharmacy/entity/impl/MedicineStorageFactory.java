package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Kuzmenko on 20.02.2015.
 */
public class MedicineStorageFactory {

    public static final String TEST_MEDICINE_STORAGE = "default";


    public static IMedicineStorage makeStorage(String type) {
        switch (type) {
            case TEST_MEDICINE_STORAGE:
                return createTestMedicineStorage();
            default:
                return createTestMedicineStorage();
        }
    }

    private static IMedicineStorage createTestMedicineStorage() {

        List<IMedicineCategory> categoryList = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            List<IMedicineItem> itemList = new ArrayList<>();
            for (int k = 1; k < 20; k++) {
                itemList.add(new MedicineItem("Item " + (i * k), "", "Description " + (i * k)));
            }
            categoryList.add(new MedicineCategory("Category name " + i, "", itemList));
        }
        return new MedicineStorage(categoryList);
    }
}
