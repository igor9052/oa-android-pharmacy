package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.os.Parcel;

public class MedicineItem implements IMedicineItem {

    public static final Creator<MedicineItem> CREATOR = new Creator<MedicineItem>() {
        @Override
        public MedicineItem createFromParcel(Parcel source) {
            return new MedicineItem(source);
        }

        @Override
        public MedicineItem[] newArray(int size) {
            return new MedicineItem[size];
        }
    };
    private String name;
    private String image;
    private String description;
    private int imageId;
    private String categoryName;

    public MedicineItem(String name, String image, String description, String categoryName) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
    }

    public MedicineItem(String name, int imageId, String description, String categoryName) {
        this.name = name;
        this.imageId = imageId;
        this.description = description;
        this.categoryName = categoryName;
    }

    private MedicineItem(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
        imageId = in.readInt();
        categoryName = in.readString();
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
    public String getImage() {
        return image;
    }

    @Override
    public int getImageId() {
        return imageId;
    }

    @Override
    public String getCategory() {
        return categoryName;
    }

    @Override
    public String toString() {
        return "MedicineItem{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", imageId=" + imageId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineItem item = (MedicineItem) o;

        if (imageId != item.imageId) return false;
        if (categoryName != null ? !categoryName.equals(item.categoryName) : item.categoryName != null)
            return false;
        if (description != null ? !description.equals(item.description) : item.description != null)
            return false;
        if (image != null ? !image.equals(item.image) : item.image != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + imageId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    //Parcelable methods
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getDescription());
        dest.writeString(getImage());
        dest.writeInt(getImageId());
        dest.writeString(getCategory());
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
