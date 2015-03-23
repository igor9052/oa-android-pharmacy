package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineCategory;
import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MedicineCategory implements IMedicineCategory<MedicineProduct> {

    public static final Creator<MedicineCategory> CREATOR = new Creator<MedicineCategory>() {
        public MedicineCategory createFromParcel(Parcel source) {
            return new MedicineCategory(source);
        }

        public MedicineCategory[] newArray(int size) {
            return new MedicineCategory[size];
        }
    };
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    private int imageId;
    @SerializedName("items")
    private List<MedicineProduct> items;

    public MedicineCategory() {
    }

    public MedicineCategory(Integer id, String name, String imageUrl, int imageId, List<MedicineProduct> items) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
        this.items = items;
    }

    private MedicineCategory(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.imageUrl = in.readString();
        this.imageId = in.readInt();
        this.items = new ArrayList<MedicineProduct>();
        in.readList(this.items, this.items.getClass().getClassLoader());
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public List<MedicineProduct> getItems() {
        return items;
    }

    public void setItems(List<MedicineProduct> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MedicineCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageId=" + imageId +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineCategory that = (MedicineCategory) o;

        if (imageId != that.imageId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null)
            return false;
        if (items != null ? !items.equals(that.items) : that.items != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + imageId;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.imageId);
        dest.writeList(this.items);
    }
}
