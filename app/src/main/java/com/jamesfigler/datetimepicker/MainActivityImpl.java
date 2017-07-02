package com.jamesfigler.datetimepicker;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivityImpl {

    private DatePickerDialogFactory datePickerDialogFactory = new DatePickerDialogFactory();
    private Calendar today = Calendar.getInstance();
    private TextInputEditText dateEditText;
    private DatePickerDialog datePickerDialog;

    public void onCreate(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);
    }

    public void onPostCreate(MainActivity activity) {
        initDateEditText(activity);
        initDatePickerDialog(activity);
        setDateClickListener();
    }

    private void initDateEditText(MainActivity activity) {
        dateEditText = (TextInputEditText) activity.findViewById(R.id.date_input_view_text);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String formattedDate = sdf.format(today.getTime());
        dateEditText.setText(formattedDate);
    }

    private void initDatePickerDialog(MainActivity activity) {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                dateEditText.setText(sdf.format(calendar.getTime()));
            }
        };

        datePickerDialog = datePickerDialogFactory.make(activity, listener,
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
    }

    private void setDateClickListener() {
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }
}
