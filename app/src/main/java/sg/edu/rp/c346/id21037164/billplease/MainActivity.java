package sg.edu.rp.c346.id21037164.pleasebill;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import sg.edu.rp.c346.id21037164.billplease.R;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText numPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView personPays;
    Button split;
    Button reset;
    EditText discount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.InputAmount);
        numPax = findViewById(R.id.InputNumPax);
        totalBill = findViewById(R.id.textAmount);
        personPays = findViewById(R.id.personPays);
        svs = findViewById(R.id.Svs);
        gst = findViewById(R.id.Gst);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        discount = findViewById(R.id.InputDiscount);


        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount.getText().toString().trim().length() != 0 && numPax.getText().toString().trim().length() != 0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
                    if (discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                    }


                    totalBill.setText("Total Bill: $" + String.format("%.2f", newAmt));


                    int numPerson = Integer.parseInt(numPax.getText().toString());

                    if (numPerson != 1)
                        personPays.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));

                    else personPays.setText("Each Pays: $" + newAmt);
                }

            }


        });}}
