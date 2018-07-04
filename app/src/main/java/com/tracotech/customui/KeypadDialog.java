package com.tracotech.customui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.tracotech.tracoshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vishalm on 03/07/18.
 */
public class KeypadDialog extends Dialog implements View.OnClickListener {

    private String inputString = "";
    @BindView(R.id.input_et)
    TextView inputTextView;
    @BindView(R.id.done_bt)
    TextView doneButton;

    private View.OnClickListener doneClickListener;

    public KeypadDialog(@NonNull Context context) {
        super(context);
    }

    public void setDoneClickListener(View.OnClickListener doneClickListener) {
        this.doneClickListener = doneClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog_keypad);
        ButterKnife.bind(this);
        setCancelable(false);
        doneButton.setOnClickListener(doneClickListener);
    }

    public void onKeyClick(View v){
        if(inputString.length() < 7) {
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
