package com.jamesfigler.datetimepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private boolean isValveOpen = true;

    public void closeValve() {
        isValveOpen = false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (isValveOpen) super.onCreate(savedInstanceState);
    }
}
