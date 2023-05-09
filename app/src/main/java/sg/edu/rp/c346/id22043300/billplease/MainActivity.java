package sg.edu.rp.c346.id22043300.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    ToggleButton tg_svs;
    ToggleButton tg_gst;
    Button split_btn;
    Button reset_btn;
    RadioGroup rg_paymethod;
    EditText amt;
    EditText pax;
    EditText dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.textViewFinal);
        tg_svs = findViewById(R.id.svs_tg);
        tg_gst = findViewById(R.id.gst_tg);
        split_btn = findViewById(R.id.split_btn);
        reset_btn = findViewById(R.id.reset_btn);
        rg_paymethod = findViewById(R.id.RadioGroupPay);
        amt = findViewById(R.id.amt);
        pax = findViewById(R.id.pax);
        dis = findViewById(R.id.discount);


        split_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for the action
                String stringResponse = "";
                double gst = 0;
                double svs = 0;

                if (tg_gst.isChecked()) {
                    gst = 0.08;
                } else {
                    gst = 0;
                }

                if (tg_svs.isChecked()) {
                    svs = 0.10;
                } else {
                    svs = 0;
                }

                double total = Integer.parseInt(amt.getText().toString());
                double discount = Integer.parseInt(dis.getText().toString());
                int pax_s = Integer.parseInt(pax.getText().toString());

                double after_dis = ( (total + (total*gst) + (total*svs)) - (total*(discount/100) ));
                double each = after_dis/pax_s;

                int checkedRadioId = rg_paymethod.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.cash_m){
                    // Write the code when male selected
                    stringResponse = "Total Bill: $" + String.format("%.2f",after_dis) + "\n Each Pays: $" + String.format("%.2f",each) + " in cash";
                }
                else{
                    // Write the code when female selected
                    stringResponse = "Total Bill: $" + String.format("%.2f",after_dis) + "\n Each Pays: $" + String.format("%.2f",each) + " via PayNow to 912345678";
                }
                tvDisplay.setText(stringResponse);
            }
        });
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action
                amt.setText(null);
                pax.setText(null);
                tg_svs.setChecked(false);
                tg_gst.setChecked(false);
                dis.setText(null);
                rg_paymethod.setActivated(false);
                tvDisplay.setText(null);
            }
        });
    }
}