package com.tracotech.tracoshop;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tracotech.adapters.ProductsGridAdapter;
import com.tracotech.customui.KeypadDialog;
import com.tracotech.interfaces.AddToCartListener;
import com.tracotech.interfaces.NetworkResponseChecker;
import com.tracotech.models.ResponseModel;
import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.viewmodels.ProductListingViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListingActivity extends ParentAppCompatActivity {

    @BindView(R.id.sp_distributer)
    Spinner distributorDropdown;
    @BindView(R.id.rv_products)
    RecyclerView productsRecyclerview;
    @BindView(R.id.drop_down)
    ImageView dropDownIcon;

    private ProductListingViewModel viewModel;
    private ResponseModel productsResponseModel = new ResponseModel();
    private ResponseModel cartResponseModel = new ResponseModel();
    private ProductsGridAdapter adapter;
    private KeypadDialog keypadDialog;

    @OnClick(R.id.bt_top_left)
    public void onBackPressed(){
        finish();
    }

    @OnClick(R.id.bt_checkout)
    public void onCheckout(){
        openCartActivity();
    }

    private ArrayList<String> dropDownItems = new ArrayList<>();
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        setResources(R.layout.activity_product_listing);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ProductListingViewModel.class);
        initialiseToolbar();
        initDropdown();
        initProductsGrid();

    }

    private void initProductsGrid() {
        keypadDialog = new KeypadDialog(this);
        keypadDialog.setDoneClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keypadDialog.dismiss();
                Toast.makeText(ProductListingActivity.this,
                        keypadDialog.getProductsUiModel().getName()+" "+keypadDialog.getInputValue(),
                        Toast.LENGTH_LONG).show();
                updateList(keypadDialog.getPosition(), keypadDialog.getProductsUiModel(), keypadDialog.getInputValue());
                keypadDialog.clearInput();
            }
        });
        initProductsListObservers();
        initCartProductsObservers();
        fetchCartProducts();
        adapter = new ProductsGridAdapter();
        adapter.setAddToCartListener(new AddToCartListener() {
            @Override
            public void onAdd(ProductsUiModel productsUiModel, int position) {
                keypadDialog.setProductsUiModel(productsUiModel);
                keypadDialog.setInput(productsUiModel.getInCartCount());
                keypadDialog.setPosition(position);
                keypadDialog.show();
            }
        });
        productsRecyclerview.setLayoutManager( new GridLayoutManager(this, 2));
        productsRecyclerview.setAdapter(adapter);
    }

    private void initCartProductsObservers() {
        cartResponseModel.getToLogout().observe(this, logoutObserver);
        cartResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                dismissProgressDialog();
                if (aBoolean) {
                    fetchProducts();
                }
            }
        });

        cartResponseModel.getErrorMessage().observe(this, errorObserver);
    }

    private void initProductsListObservers() {
        productsResponseModel.getToLogout().observe(this, logoutObserver);
        productsResponseModel.getStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                dismissProgressDialog();
                if (aBoolean) {
                    List<ProductsUiModel> productsUiModelList = viewModel.getProductsList();
                    adapter.replaceAllProducts(productsUiModelList, viewModel.getCartProductsList());

                }
            }
        });

        productsResponseModel.getErrorMessage().observe(this, errorObserver);
    }

    private void fetchProducts(){
        showProgressDialog();
        viewModel.fetchProducts(new NetworkResponseChecker(){}, productsResponseModel);
    }

    private void fetchCartProducts(){
        showProgressDialog();
        viewModel.fetchCartProducts(new NetworkResponseChecker(){}, cartResponseModel);
    }

    private void updateList(int position, ProductsUiModel productsUiModel, int keypadInput) {
        if(keypadInput > 0) {
            productsUiModel.setInCart(true);
            productsUiModel.setInCartCount(keypadInput);
            viewModel.getProductsList().set(position, productsUiModel);
            adapter.replaceProduct(position, productsUiModel);
            viewModel.addToCart(productsUiModel);
        }
    }

    private void initialiseToolbar() {
        setToolbarLeftIcon(R.drawable.ic_back_arrow);
        if(viewModel.isCartActive()){
            setToolbarRightIcon(R.drawable.cart_active);
        } else
            setToolbarRightIcon(R.drawable.card_inactive);
        showSearchBar();
    }

    private void initDropdown() {
        dropDownItems.add("Stardust");
        dropDownItems.add("Zenith");
        dropDownItems.add("GoodHome");
        dropDownItems.add("Global");
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownItems);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        distributorDropdown.setAdapter(aa);
        dropDownIcon.setOnClickListener(this);
    }

    @Override
    public void doInitialSetup() {

    }

    public void keypadButtonClick(View view){
        keypadDialog.onKeyClick(view);
    }

    @Override
    public void onClickWithId(int resourceId) {
        switch (resourceId){
            case R.id.bt_top_left:
                onBackPressed();
                finish();
                break;
            case R.id.bt_top_right:
                openCartActivity();
                break;
            case R.id.drop_down:
                distributorDropdown.performClick();
                break;
        }
    }

    private void openCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
