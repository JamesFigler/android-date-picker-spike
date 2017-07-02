package com.jamesfigler.datetimepicker;

import android.app.DatePickerDialog;
import android.content.Context;

public class DatePickerDialogFactory {
    public DatePickerDialog make(Context context, DatePickerDialog.OnDateSetListener listener,
                                 int year, int month, int dayOfMonth) {
        return new DatePickerDialog(context, listener, year, month, dayOfMonth);
    }
}
