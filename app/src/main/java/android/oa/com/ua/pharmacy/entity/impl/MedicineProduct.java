package android.oa.com.ua.pharmacy.entity.impl;

import android.oa.com.ua.pharmacy.entity.IMedicineProduct;
import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class MedicineProduct implements IMedicineProduct {

    public static final Creator<MedicineProduct> CREATOR = new Creator<MedicineProduct>() {
        public MedicineProduct createFromParcel(Parcel source) {
            return new MedicineProduct(source);
        }

        public MedicineProduct[] newArray(int size) {
            return new MedicineProduct[size];
        }
    };
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("image_url")
    private String imageUrl;
    private Integer imageId;
    @SerializedName("category_name")
    private String categoryName;
    private Integer categoryId;

    public MedicineProduct() {
    }

    public MedicineProduct(Integer id, String name, String description, String imageUrl, Integer imageId, String categoryName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.imageId = imageId;
        this.categoryName = categoryName;
        this.categoryId = 0;
    }

    private MedicineProduct(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.description = in.readString();
        this.imageUrl = in.readString();
        this.imageId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.categoryName = in.readString();
        this.categoryId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    @Override
    public String toString() {
        return "MedicineProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", imageId=" + imageId +
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
        if (imageId != null ? !imageId.equals(that.imageId) : that.imageId != null) return false;
        if (imageUrl != null ? !imageUrl.equals(that.imageUrl) : that.imageUrl != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    @Override
    public String getCategory() {
        return getCategoryName();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.imageUrl);
        dest.writeValue(this.imageId);
        dest.writeString(this.categoryName);
        dest.writeValue(this.categoryId);
    }
}
