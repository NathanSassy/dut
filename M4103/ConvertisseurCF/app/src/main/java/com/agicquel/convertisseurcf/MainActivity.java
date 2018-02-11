package com.agicquel.convertisseurcf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // DATA
    private int selectedUnit;

    // GUI
    private TextView mResult;
    private TextView mValue;
    private Button mConvertBtn;
    private RadioGroup mRadioGroup;
    private RadioButton mRadBtnC;
    private RadioButton mRadBtnF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedUnit = -1;

        mResult = findViewById(R.id.textview_result);
        mValue = findViewById(R.id.editText);
        mConvertBtn = findViewById(R.id.button_result);
        mRadioGroup = findViewById(R.id.radioGroup);
        mRadBtnC = findViewById(R.id.radioButtonC);
        mRadBtnF = findViewById(R.id.radioButtonF);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedUnit = checkedId;
            }
        });

        mConvertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mValue.getText().toString().isEmpty()) {
                    mResult.setText(R.string.app_err_novalue);
                    mResult.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else if(selectedUnit == -1) {
                    mResult.setText(R.string.app_err_nounit);
                    mResult.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
                else if(selectedUnit == mRadBtnC.getId()) {
                    mResult.setText(getString(R.string.celsius) + convertFtoC(Double.valueOf(mValue.getText().toString())));
                    mResult.setTextColor(getResources().getColor(android.R.color.black));
                }
                else if(selectedUnit == mRadBtnF.getId()) {
                    mResult.setText(getString(R.string.fahrenheit) + convertCtoF(Double.valueOf(mValue.getText().toString())));
                    mResult.setTextColor(getResources().getColor(android.R.color.black));
                }
            }
        });

    }

    private double convertFtoC(double celsius) {
        return ((celsius*9)/5)+32;
    }

    private double convertCtoF(double fahrenheit) {
        return ((fahrenheit-32)*5)/9;
    }
}
