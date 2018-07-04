package com.tracotech.models.uimodels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vishalm on 29/06/18.
 */
public class ProductsUiModel implements Parcelable {

    private int id;
    private String sku;
    private String barcode;
    private String name;
    private String brandName;
    private String category;
    private String weight;
    private String weightUnit;
    private String imageUrl;
    private String brandId;
    private boolean inCart = false;
    private int inCartCount = 0;

    public static String ID = "id";
    public static String SKU = "sku";
    public static String BARCODE = "barcode";
    public static String NAME = "name";
    public static String BRAND_NAME = "brand_name";
    public static String CATEGORY = "catrgory";
    public static String WEIGHT = "weight";
    public static String WEIGHT_UNIT = "weight_unit";
    public static String IMAGE_URL = "image_url";
    public static String BRAND_ID = "brand_id";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setInCart(boolean inCart){
        this.inCart = inCart;
    }
    public boolean isInCart(){
        return inCart;
    }

    public int getInCartCount() {
        return inCartCount;
    }

    public void setInCartCount(int inCartCount) {
        this.inCartCount = inCartCount;
    }

    public ProductsUiModel(){

    }

    protected ProductsUiModel(Parcel in) {
        id = in.readInt();
        sku = in.readString();
        barcode = in.readString();
        name = in.readString();
        brandName = in.readString();
        category = in.readString();
        weight = in.readString();
        weightUnit = in.readString();
        imageUrl = in.readString();
        brandId = in.readString();
        inCart = in.readByte() != 0x00;
        inCartCount = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(sku);
        dest.writeString(barcode);
        dest.writeString(name);
        dest.writeString(brandName);
        dest.writeString(category);
        dest.writeString(weight);
        dest.writeString(weightUnit);
        dest.writeString(imageUrl);
        dest.writeString(brandId);
        dest.writeByte((byte) (inCart ? 0x01 : 0x00));
        dest.writeInt(inCartCount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProductsUiModel> CREATOR = new Parcelable.Creator<ProductsUiModel>() {
        @Override
        public ProductsUiModel createFromParcel(Parcel in) {
            return new ProductsUiModel(in);
        }

        @Override
        public ProductsUiModel[] newArray(int size) {
            return new ProductsUiModel[size];
        }
    };
}