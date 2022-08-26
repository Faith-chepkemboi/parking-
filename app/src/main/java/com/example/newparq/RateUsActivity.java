package com.example.newparq;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RateUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);

        //showing rating dialog
        RateUsDialog rateUsDialog = new RateUsDialog(RateUsActivity.this);
        rateUsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(cn.pedant.SweetAlert.R.color.float_transparent)));
        rateUsDialog.setCancelable(false);
        rateUsDialog.show();
    }
}