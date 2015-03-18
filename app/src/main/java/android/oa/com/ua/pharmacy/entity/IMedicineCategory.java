package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

import java.util.List;

public interface IMedicineCategory<T extends IMedicineProduct> extends Parcelable {

    Integer getId();

    String getName();

    String getImageUrl();

    List<T> getItems();
}
