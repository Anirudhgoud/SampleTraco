package com.tracotech.models.uimodels;

/**
 * Created by vishalm on 29/06/18.
 */
public class ProductsUiModel {

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
}
