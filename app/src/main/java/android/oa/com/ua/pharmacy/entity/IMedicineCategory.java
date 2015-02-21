package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

import java.util.List;

public interface IMedicineCategory extends Parcelable {
    String getName();

    String getImage();

    List<IMedicineItem> getItems();
}
