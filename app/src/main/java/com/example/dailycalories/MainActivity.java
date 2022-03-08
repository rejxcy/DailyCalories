package com.example.dailycalories;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtHeight, edtWeight;
    private RadioGroup buttonStatus;
    private TextView result;
    private double height, weight, bmi, workLoad;
    private String posture;

    //  取得使用者物件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnCalculate);
        button.setOnClickListener(this);
        //顯示選項資訊
        buttonStatus = findViewById(R.id.rmRadioGroup);
        buttonStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int chackId) {
                switch (chackId) {
                    case R.id.easy:
                        Toast.makeText(MainActivity.this, "較少運動", Toast.LENGTH_SHORT).show();
                        workLoad = 30;
                        break;
                    case R.id.middle:
                        Toast.makeText(MainActivity.this, "日常運動量", Toast.LENGTH_SHORT).show();
                        workLoad = 35;
                        break;
                    case R.id.hard:
                        Toast.makeText(MainActivity.this, "大量運動", Toast.LENGTH_SHORT).show();
                        workLoad = 40;
                        break;
                }
            }
        });
    }

    //   clickEvent
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        // text value
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        result = findViewById(R.id.txtResult);
        // double format
        DecimalFormat nf = new DecimalFormat("0.00");
        //BMI
        try {
            //身高
            height = Double.parseDouble(edtHeight.getText().toString()) / 100;
            //體重
            weight = Double.parseDouble(edtWeight.getText().toString());
            //計算出BMI值
            bmi = weight / (height * height);
            if (bmi < 18.5) {
                posture = "過輕";
            } else if (bmi >= 18.5 && bmi < 24) {
                posture = "正常";
            } else if (bmi >= 24) {
                posture = "過重";
            }
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "需要輸入身高體重唷!", Toast.LENGTH_SHORT).show();
        }
        //運動量
        buttonStatus = findViewById(R.id.rmRadioGroup);
        buttonStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int chackId) {
                switch (chackId) {
                    case R.id.easy:
                        Toast.makeText(MainActivity.this, "較少運動", Toast.LENGTH_SHORT).show();
                        workLoad = 30;
                        break;
                    case R.id.middle:
                        Toast.makeText(MainActivity.this, "日常運動量", Toast.LENGTH_SHORT).show();
                        workLoad = 35;
                        break;
                    case R.id.hard:
                        Toast.makeText(MainActivity.this, "大量運動", Toast.LENGTH_SHORT).show();
                        workLoad = 40;
                        break;
                }
            }
        });

        result.setText("您的BMI: " + nf.format(bmi) + " 屬於 " + posture +
                "\n每日熱量所需: " + workLoad * weight + " 大卡" +
                "\n建議每日熱量: " + (workLoad - 5) * weight + " 大卡");
    }
}