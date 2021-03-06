package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineStorage;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class MedicineStorage implements IMedicineStorage<MedicineCategory> {

    public static final Parcelable.Creator<MedicineStorage> CREATOR = new Parcelable.Creator<MedicineStorage>() {
        public MedicineStorage createFromParcel(Parcel source) {
            return new MedicineStorage(source);
        }

        public MedicineStorage[] newArray(int size) {
            return new MedicineStorage[size];
        }
    };
    private List<MedicineCategory> categories;

    public MedicineStorage() {
    }

    public MedicineStorage(List<MedicineCategory> categories) {
        this.categories = categories;
    }

    private MedicineStorage(Parcel in) {
        in.readTypedList(categories, MedicineCategory.CREATOR);
    }

    @Override
    public List<MedicineCategory> getCategories() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(categories);
    }
}
