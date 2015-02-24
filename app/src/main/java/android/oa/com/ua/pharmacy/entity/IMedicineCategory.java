package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

import java.util.List;

public interface IMedicineCategory extends Parcelable {

    Integer getId();

    String getName();

    Integer getImage();

    List<IMedicineProduct> getItems();
}
