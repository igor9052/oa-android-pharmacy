package android.oa.com.ua.pharmacy.entity;

import android.os.Parcelable;

/**
 * Created by igor9_000 on 19.02.2015.
 */
public interface IMedicineProduct extends Parcelable {

    Integer getId();

    void setId(Integer id);

    String getName();

    String getDescription();

    String getImageUrl();

    String getCategory();

    Integer getImageId();

    Integer getCategoryId();

    void setCategoryId(Integer categoryId);
}
