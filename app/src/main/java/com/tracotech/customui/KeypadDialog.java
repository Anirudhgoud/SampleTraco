package com.tracotech.customui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.tracotech.models.uimodels.ProductsUiModel;
import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vishalm on 03/07/18.
 */
public class KeypadDialog extends Dialog implements View.OnClickListener {

    private String inputString = "";
    private ProductsUiModel productsUiModel;
    @BindView(R.id.input_et)
    TextView inputTextView;
    @BindView(R.id.done_bt)
    TextView doneButton;

    private View.OnClickListener doneClickListener;
    private int position;

    public KeypadDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.layout_dialog_keypad);
        ButterKnife.bind(this);
    }

    public void setDoneClickListener(View.OnClickListener doneClickListener) {
        this.doneClickListener = doneClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        doneButton.setOnClickListener(doneClickListener);
    }

    public int getInputValue(){
        try {
            return Integer.parseInt(inputString);
        }catch (NumberFormatException e){
            return 0;
        }
    }

    public ProductsUiModel getProductsUiModel() {
        return productsUiModel;
    }

    public void setProductsUiModel(ProductsUiModel productsUiModel) {
        this.productsUiModel = productsUiModel;
    }

    public void clearInput(){
        inputString = "";
        inputTextView.setText("");
    }

    public void setInput(int cartQuantity){
        inputString = ""+cartQuantity;
        if(inputTextView != null)
            inputTextView.setText(cartQuantity+"");
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void onKeyClick(View v){
        if(inputString.length() < 7) {
            inputString = inputString.equals("0") ? "" : inputString;
            switch (v.getId()) {
                case R.id.one:
                    inputString += "1";
                    break;
                case R.id.two:
                    inputString += "2";
                    break;
                case R.id.three:
                    inputString += "3";
                    break;
                case R.id.four:
                    inputString += "4";
                    break;
                case R.id.five:
                    inputString += "5";
                    break;
                case R.id.six:
                    inputString += "6";
                    break;
                case R.id.seven:
                    inputString += "7";
                    break;
                case R.id.eight:
                    inputString += "8";
                    break;
                case R.id.nine:
                    inputString += "9";
                    break;
                case R.id.zero:
                    inputString += "0";
                    break;
                case R.id.backspace:
                    if (inputString.length() > 0) {
                        inputString = inputString.substring(0, inputString.length() - 1);
                    }
                    break;
            }
            inputTextView.setText(inputString);
        } else if(inputString.length() == 7 && v.getId() == R.id.backspace){
            inputString = inputString.substring(0, inputString.length() - 1);
            inputTextView.setText(inputString);
        }
    }

    @Override
    public void onClick(View v) {

    }
}
