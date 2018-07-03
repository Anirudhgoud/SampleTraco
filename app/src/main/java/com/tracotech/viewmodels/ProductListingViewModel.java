package com.tracotech.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.tracotech.constants.SharedPreferenceKeys;
import com.tracotech.constants.UrlConstants;
import com.tracotech.interfaces.NetworkAPICallback;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.services.network.NetworkService;
import com.tracotech.services.storage.LocalStorageService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishalm on 02/07/18.
 */
public class ProductListingViewModel extends AndroidViewModel {

    List<ProductsUiModel> products = new ArrayList<>();

    public ProductListingViewModel(@NonNull Application application) {
        super(application);
    }

    public void fetchProducts(NetworkResponseChecker networkResponseChecker,
                              ResponseModel productsResponseModel) {
        String url = UrlConstants.PRODUCT_LIST+"?owners=1";
        NetworkService.sharedInstance().getNetworkClient().makeGetRequest(getApplication(),
                url, true, new NetworkAPICallback() {
                    @Override
                    public void onNetworkResponse(int type, Object response, String errorMessage) {
                        products = parseProducts(response);
                        networkResponseChecker.checkResponse(type, errorMessage,
                                productsResponseModel, true, false);
                    }
                });

    }

    public List<ProductsUiModel> getProductsList(){
        return products;
    }

    private List<ProductsUiModel> parseProducts(Object response) {
        ArrayList<ProductsUiModel> productsUiModels = new ArrayList<>();
        JSONArray dataJson = ((JSONObject) response).optJSONArray("data");
        if(dataJson != null){
            for(int i=0;i<dataJson.length();i++){
                JSONObject productJson = dataJson.optJSONObject(i);
                ProductsUiModel productsUiModel = new ProductsUiModel();
                productsUiModel.setId(productJson.optInt(ProductsUiModel.ID));
                productsUiModel.setSku(productJson.optString(ProductsUiModel.SKU));
                productsUiModel.setBarcode(productJson.optString(ProductsUiModel.BARCODE));
                productsUiModel.setName(productJson.optString(ProductsUiModel.NAME));
                productsUiModel.setCategory(productJson.optString(ProductsUiModel.CATEGORY));
                productsUiModel.setBrandName(productJson.optString(ProductsUiModel.BRAND_NAME));
                productsUiModel.setWeight(productJson.optString(ProductsUiModel.WEIGHT));
                productsUiModel.setWeightUnit(productJson.optString(ProductsUiModel.WEIGHT_UNIT));
                productsUiModel.setImageUrl(productJson.optString(ProductsUiModel.IMAGE_URL));
                productsUiModel.setBrandId(productJson.optString(ProductsUiModel.BRAND_ID));
                productsUiModels.add(productsUiModel);
            }
        }
        return productsUiModels;
    }

    public boolean isCartActive() {
        int count = LocalStorageService.sharedInstance().getLocalFileStore().getInt(getApplication(),
                SharedPreferenceKeys.CART_ITEMS_COUNT);
        if(count > 0)
            return true;
        else return false;
    }
}
