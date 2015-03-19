package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

import java.util.List;

public interface IMedicineStorage<T extends IMedicineCategory> extends Parcelable {
    List<T> getCategories();

    List<String> getCategoryNames();
}
