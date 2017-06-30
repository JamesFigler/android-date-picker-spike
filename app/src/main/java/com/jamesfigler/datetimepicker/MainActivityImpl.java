package com.jamesfigler.datetimepicker;

import android.view.View;
import android.widget.Button;

public class MainActivityImpl {
    public void onCreate(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);
    }

    public void onPostCreate(MainActivity activity) {
        final Button showDateTimePickerButton = (Button) activity.findViewById(R.id.show_date_picker_button);

        showDateTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerButton.setText("I've been clicked");
            }
        });
    }
}
