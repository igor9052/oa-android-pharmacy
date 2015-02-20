package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineItem;
import android.os.Parcel;
import android.os.Parcelable;

public class MedicineItem implements IMedicineItem, Parcelable {

    private String name;
    private String image;
    private String description;
    private int imageId;

    public MedicineItem(String name, String image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public MedicineItem(String name, int imageId, String description) {
        this.name = name;
        this.imageId = imageId;
        this.description = description;
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
    public String toString() {
        return "MedicineItem{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicineItem that = (MedicineItem) o;

        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


    //Parcelable methods
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeString(getDescription());
        dest.writeString(getName());
    }

    private MedicineItem(Parcel in) {
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

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


}
