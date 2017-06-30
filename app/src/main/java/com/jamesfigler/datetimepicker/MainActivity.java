package com.jamesfigler.datetimepicker;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    private MainActivityImpl impl = new MainActivityImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        impl.onCreate(this);
    }
}
