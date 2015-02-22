package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

/**
 * Created by igor9_000 on 19.02.2015.
 */
public interface IMedicineItem extends Parcelable {
    String getName();

    String getDescription();

    String getImage();

    int getImageId();

    String getCategory();
}
