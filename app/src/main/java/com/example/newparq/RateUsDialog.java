package com.example.newparq;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class RateUsDialog extends Dialog {
    public RateUsDialog(@NonNull Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);

        setContentView(R.layout.rate_us_dialog_layout);
    }

}