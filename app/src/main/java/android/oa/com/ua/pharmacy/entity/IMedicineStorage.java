package android.oa.com.ua.pharmacy.entity;

import java.util.List;

public interface IMedicineStorage<T extends IMedicineCategory> {
    List<T> getCategories();

    List<String> getCategoryNames();
}
