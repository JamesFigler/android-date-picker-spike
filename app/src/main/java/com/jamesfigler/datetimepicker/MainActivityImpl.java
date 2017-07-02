package com.jamesfigler.datetimepicker;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_2;

public class MainActivityImpl {

    private DatePickerDialogFactory datePickerDialogFactory = new DatePickerDialogFactory();
    private TextInputEditText dateEditText;
    private DatePickerDialog datePickerDialog;
    private Calendar today;

    public void onCreate(MainActivity activity) {
        activity.setContentView(R.layout.activity_main);
    }

    public void onPostCreate(MainActivity activity) {
        today = Calendar.getInstance();
        initDateEditText(activity);
        initDatePickerDialog();
        setDateClickListener(activity);
    }

    private void initDateEditText(MainActivity activity) {
        dateEditText = (TextInputEditText) activity.findViewById(R.id.date_input_view_text);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String formattedDate = sdf.format(today.getTime());
        dateEditText.setText(formattedDate);
    }

    private void initDatePickerDialog() {
        datePickerDialog = datePickerDialogFactory.make();
        datePickerDialog.setVersion(VERSION_2);
        datePickerDialog.setMinDate(today);
        datePickerDialog.vibrate(false);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
                Calendar date = Calendar.getInstance();
                date.set(year, monthOfYear, dayOfMonth);
                String formattedDate = sdf.format(date.getTime());
                dateEditText.setText(formattedDate);
            }
        });
    }

    private void setDateClickListener(final MainActivity activity) {
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show(activity.getFragmentManager(), null);
            }
        });
    }
}
