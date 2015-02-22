package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;

import java.util.ArrayList;
import java.util.List;

public class MedicineStorage implements IMedicineStorage {

    private List<IMedicineCategory> categories = new ArrayList<>();

    public MedicineStorage() {
    }

    public MedicineStorage(List<IMedicineCategory> categories) {
        this.categories = categories;
    }

    @Override
    public List<IMedicineCategory> getCategories() {
        return categories;
    }

    @Override
    public List<String> getCategoryNames() {
        List<String> categoryNames = new ArrayList<>(categories.size());
        for (IMedicineCategory category : categories) {
            categoryNames.add(category.getName());
        }
        return categoryNames;
    }

    @Override
    public String toString() {
        return "MedicineStorage{" +
                "categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineStorage that = (MedicineStorage) o;

        if (categories != null ? !categories.equals(that.categories) : that.categories != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return categories != null ? categories.hashCode() : 0;
    }
}
