package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
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

    private Integer id;
    private String name;
    private Integer image;
    private List<IMedicineProduct> items = new ArrayList<>();

    protected MedicineCategory(Integer id, String name, Integer image, List<IMedicineProduct> items) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.items = items;
    }

    private MedicineCategory(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readInt();
        in.readList(items, items.getClass().getClassLoader());
    }

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getImage() {
        return image;
    }

    @Override
    public List<IMedicineProduct> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "MedicineCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineCategory that = (MedicineCategory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
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
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(image);
        dest.writeList(items);
    }
}
