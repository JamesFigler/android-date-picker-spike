package com.jamesfigler.datetimepicker;

import android.support.design.widget.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivityImpl {
    public void onCreate(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);
    }

    public void onPostCreate(MainActivity activity) {
        initDateEditText(activity);
    }

    private void initDateEditText(MainActivity activity) {
        TextInputEditText dateEditText = (TextInputEditText) activity.findViewById(R.id.date_input_view_text);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String formattedDate = sdf.format(Calendar.getInstance().getTime());
        dateEditText.setText(formattedDate);
    }
}
