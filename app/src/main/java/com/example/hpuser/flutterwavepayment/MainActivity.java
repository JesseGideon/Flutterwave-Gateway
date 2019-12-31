package com.example.hpuser.flutterwavepayment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button paybtn = findViewById(R.id.paybtn);
        TextView totalamount = findViewById(R.id.totalamount);
        String total = totalamount.getText().toString();

        final String h = String.valueOf(System.currentTimeMillis());

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paym = "50";
                Double amount = Double.parseDouble(paym);
                //creating instance or Rave
                 new RavePayManager(MainActivity.this).setAmount(amount)
                         .setCountry("NG")
                        .setCurrency("NGN")
                        .setEmail("jessegideon@yahoo.com")
                        .setfName("Gideon")
                        .setlName("Test")
                        .setNarration("Payment using flutterwave on Rave")
                        .setPublicKey("FLWPUBK-c99d1060344acf8a1c2ac099681c97fa-X")
                        .setEncryptionKey("942b41c01d40a343363f730b")
                        .setTxRef( h + "Ref")
                        //types of payment methods
                        .acceptAccountPayments(true)
                        .acceptCardPayments(true)

                        .shouldDisplayFee(true)
                        .showStagingLabel(false)
                        .onStagingEnv(true)
                        //.withTheme(R.style.MyCustomTheme)
                        .initialize();
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS ", Toast.LENGTH_SHORT).show();
                Intent gt = new Intent(getApplicationContext(),Success.class);
                startActivity(gt);

            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
