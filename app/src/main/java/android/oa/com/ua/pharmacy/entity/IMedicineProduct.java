package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

/**
 * Created by igor9_000 on 19.02.2015.
 */
public interface IMedicineProduct extends Parcelable {

    Integer getId();

    String getName();

    String getDescription();

    Integer getImage();

    String getCategory();
}
