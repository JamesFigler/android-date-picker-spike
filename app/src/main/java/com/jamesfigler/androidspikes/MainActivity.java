package com.jamesfigler.androidspikes;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class MainActivity extends BaseActivity {

    private MainActivityImpl impl = new MainActivityImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        impl.onCreate(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        impl.onPostCreate(this);
    }
}
