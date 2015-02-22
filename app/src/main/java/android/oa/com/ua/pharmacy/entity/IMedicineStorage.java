package android.oa.com.ua.pharmacy.entity;

import java.util.List;

public interface IMedicineStorage {
    List<IMedicineCategory> getCategories();

    List<String> getCategoryNames();
}
