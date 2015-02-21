package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

public class MedicineCategory implements IMedicineCategory {

    public static final Creator<MedicineCategory> CREATOR = new Creator<MedicineCategory>() {
        @Override
        public MedicineCategory createFromParcel(Parcel source) {
            return new MedicineCategory(source);
        }

        @Override
        public MedicineCategory[] newArray(int size) {
            return new MedicineCategory[size];
        }
    };
    private String name;
    private String image;
    private List<IMedicineItem> items = new ArrayList<>();

    protected MedicineCategory(String name, String image, List<IMedicineItem> items) {
        this.name = name;
        this.image = image;
        this.items = items;
    }

    private MedicineCategory(Parcel in) {
        name = in.readString();
        image = in.readString();
        in.readList(items, items.getClass().getClassLoader());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public List<IMedicineItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "MedicineCategory{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", items=" + items +
                '}';
    }

    //Parcelable methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineCategory that = (MedicineCategory) o;

        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeList(items);
    }
}
