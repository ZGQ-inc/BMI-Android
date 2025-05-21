package com.zgqinc.bmi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity {
    EditText h, w;
    TextView r;

    public void onCreate(Bundle b) {
        super.onCreate(b);

        LinearLayout l = new LinearLayout(this);
        l.setOrientation(1); // VERTICAL

        TextView pad = new TextView(this);
        pad.setText("\n\n");
        l.addView(pad);

        h = new EditText(this);
        h.setHint("m");
        h.setInputType(8194); // numberDecimal
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

        btn.setOnClickListener(v -> calc());

        setContentView(l);
    }

    void calc() {
        String hs = h.getText().toString(), ws = w.getText().toString();
        if (hs.isEmpty() || ws.isEmpty()) {
            r.setText("invalid");
            return;
        }

        float hf = Float.parseFloat(hs), wf = Float.parseFloat(ws);
        if (hf <= 0 || wf <= 0) {
            r.setText(">0");
            return;
        }

        float bmi = wf / (hf * hf);
        String cat = bmi < 18.5 ? "L" : bmi < 24.9 ? "N" : bmi < 29.9 ? "F" : "O";
        r.setText(String.format("BMI: %.1f\n%s", bmi, cat));
    }
}
