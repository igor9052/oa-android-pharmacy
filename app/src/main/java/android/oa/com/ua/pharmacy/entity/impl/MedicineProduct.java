package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.os.Parcel;

public class MedicineProduct implements IMedicineProduct {

    public static final Creator<MedicineProduct> CREATOR = new Creator<MedicineProduct>() {
        @Override
        public MedicineProduct createFromParcel(Parcel source) {
            return new MedicineProduct(source);
        }

        @Override
        public MedicineProduct[] newArray(int size) {
            return new MedicineProduct[size];
        }
    };

    private Integer id;
    private String name;
    private String description;
    private Integer image;
    private String categoryName;

    public MedicineProduct(Integer id, String name, String description, Integer image, String categoryName) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
    }

    private MedicineProduct(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readInt();
        categoryName = in.readString();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getImage() {
        return image;
    }

    @Override
    public String getCategory() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "MedicineProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineProduct that = (MedicineProduct) o;

        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    //Parcelable methods
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getId());
        dest.writeString(getName());
        dest.writeString(getDescription());
        dest.writeInt(getImage());
        dest.writeString(getCategory());
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
