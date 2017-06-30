package com.jamesfigler.datetimepicker;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivityImpl {

    private DatePickerDialogFactory datePickerDialogFactory = new DatePickerDialogFactory();
    private TextInputEditText dateEditText;

    public void onCreate(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);
    }

    public void onPostCreate(MainActivity activity) {
        initDateEditText(activity);
        setDateClickListener(activity);
    }

    private void initDateEditText(MainActivity activity) {
        dateEditText = (TextInputEditText) activity.findViewById(R.id.date_input_view_text);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String formattedDate = sdf.format(Calendar.getInstance().getTime());
        dateEditText.setText(formattedDate);
    }

    private void setDateClickListener(final MainActivity activity) {
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogFactory.make().show(activity.getFragmentManager(), null);
            }
        });
    }
}
