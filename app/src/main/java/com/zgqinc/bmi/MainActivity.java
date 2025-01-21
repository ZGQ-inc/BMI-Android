package com.zgqinc.bmi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        textViewResult = findViewById(R.id.textViewResult);
        Button buttonCalculate = findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }
//
//    private EditText findViewById(int editTextHeight) {
//    }

    private void calculateBMI() {
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();

        if (TextUtils.isEmpty(heightStr) || TextUtils.isEmpty(weightStr)) {
            textViewResult.setText("不是有效值");
            return;
        }

        float height = Float.parseFloat(heightStr);
        float weight = Float.parseFloat(weightStr);

        if (height <= 0 || weight <= 0) {
            textViewResult.setText("数值不能小于0");
            return;
        }

        float bmi = weight / (height * height);
        String bmiCategory = getBMICategory(bmi);

        textViewResult.setText(String.format("BMI: %.2f\n%s", bmi, bmiCategory));
    }

    private String getBMICategory(float bmi) {
        if (bmi < 18.5) {
            return "过低体重";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "正常体重";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "超重";
        } else {
            return "肥胖";
        }
    }
}
