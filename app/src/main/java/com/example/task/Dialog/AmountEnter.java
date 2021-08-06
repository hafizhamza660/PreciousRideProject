package com.example.task.Dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.Spinner;

import com.example.task.R;

public class AmountEnter extends Dialog {
    Activity activity;
    Spinner spinner_currency;
    public AmountEnter(Activity activity) {
        super(activity);
        this.activity = activity;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_amount_enter);
    }
}