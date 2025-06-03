package com.zgqinc.bmi;

import android.app.*;
import android.os.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText h, w;
    TextView r;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);

        TextView pad = new TextView(this);
        pad.setText("\n");
        l.addView(pad);

        h = new EditText(this);
        h.setHint("cm");
        h.setInputType(8194);
        l.addView(h);

        w = new EditText(this);
        w.setHint("kg");
        w.setInputType(8194);
        l.addView(w);

        Button btn = new Button(this);
        btn.setText("calc");
        l.addView(btn);

        r = new TextView(this);
        r.setTextSize(25);
        l.addView(r);

        btn.setOnClickListener(v -> {
            try {
                float hf = Float.parseFloat(h.getText().toString()) / 100;
                float wf = Float.parseFloat(w.getText().toString());
                float bmi = wf / (hf * hf);
                String cat = bmi < 18.5 ? "L" : bmi < 24.9 ? "N" : bmi < 29.9 ? "F" : "O";
                r.setText(String.format("BMI: %.1f\n%s", bmi, cat));
            } catch (Exception e) {
                r.setText("invalid");
            }
        });

        setContentView(l);
    }
}
