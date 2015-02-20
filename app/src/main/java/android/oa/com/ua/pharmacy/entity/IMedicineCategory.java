package android.oa.com.ua.pharmacy.entity;

import java.util.List;

public interface IMedicineCategory {
    String getName();
    String getImage();
    List<IMedicineItem> getItems();
}
