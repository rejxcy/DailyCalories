package com.example.dailycalories;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText edtHeight, edtWeight;
    private RadioGroup radioStatus;
    private Button button;
    private TextView result;
    private double height, weight, bmi;
    private int dailyExercise;
    private String posture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();  //取得控制物件
        setListensers();  //設定監聽事件
    }

    //取得控制物件
    private void initView() {
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        result = findViewById(R.id.txtResult);
        button = findViewById(R.id.btnCalculate);
        radioStatus = findViewById(R.id.rmRadioGroup);

        setDailyExercise();  //每日運動量
    }

    //設定監聽事件
    private void setListensers() {
        button.setOnClickListener(calories);
    }

    private View.OnClickListener calories = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DecimalFormat nf = new DecimalFormat("0.00");

            try {
                calBMI();
                result.setText("您的BMI: " + nf.format(bmi) + " 屬於 " + posture +
                        "\n每日熱量所需: " + dailyExercise * weight + " 大卡" +
                        "\n建議每日熱量: " + (dailyExercise - 5) * weight + " 大卡");
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "需要輸入身高體重唷!", Toast.LENGTH_SHORT).show();
                result.setText("請先輸入身高與體重\n確認每日活動量喔!");
            }
        }
    };

    private void calBMI() {
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
    }

    private void setDailyExercise() {
        //每日活動量
        radioStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int chackId) {
                switch (chackId) {
                    case R.id.easy:
                        Toast.makeText(MainActivity.this, "較少運動", Toast.LENGTH_SHORT).show();
                        dailyExercise = 30;
                        break;
                    case R.id.middle:
                        Toast.makeText(MainActivity.this, "日常運動量", Toast.LENGTH_SHORT).show();
                        dailyExercise = 35;
                        break;
                    case R.id.hard:
                        Toast.makeText(MainActivity.this, "大量運動", Toast.LENGTH_SHORT).show();
                        dailyExercise = 40;
                        break;
                }
            }
        });
    }

}