package com.jamesfigler.datetimepicker;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.DatePicker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityImplTest {

    @Mock
    private DatePickerDialogFactory datePickerDialogFactory;

    @Mock
    private Calendar calendar;

    @InjectMocks
    private MainActivityImpl subject;

    private MainActivity activity;
    private TextInputEditText dateEditTextView;
    private DatePickerDialog datePickerDialog;
    private DatePicker datePicker;

    @Before
    public void setUp() {
        initMocks(this);

        activity = mock(MainActivity.class);
        dateEditTextView = mock(TextInputEditText.class);
        datePickerDialog = mock(DatePickerDialog.class);
        datePicker = mock(DatePicker.class);

        when(activity.findViewById(R.id.date_input_view_text)).thenReturn(dateEditTextView);
        when(datePickerDialogFactory.make(eq(activity), any(OnDateSetListener.class),
                anyInt(), anyInt(), anyInt())).thenReturn(datePickerDialog);
        when(datePickerDialog.getDatePicker()).thenReturn(datePicker);
    }

    @Test
    public void onCreateSetsTheContentView() {
        subject.onCreate(activity);

        verify(activity).setContentView(R.layout.activity_main);
    }

    @Test
    public void itShowsTodaysDate() {
        Date date = mock(Date.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String formattedDate = sdf.format(date.getTime());

        subject.onPostCreate(activity);

        verify(dateEditTextView).setText(formattedDate);
    }

    @Test
    public void itInitializesTheDatePickerDialog() {
        when(calendar.getTimeInMillis()).thenReturn(1234L);

        subject.onPostCreate(activity);

        verify(datePickerDialogFactory).make(eq(activity), any(OnDateSetListener.class), anyInt(), anyInt(), anyInt());
        verify(datePicker).setMinDate(1234L);
    }

    @Test
    public void itShowsTheDatePickerDialogWhenClickingOnTheDate() {
        subject.onPostCreate(activity);

        ArgumentCaptor<View.OnClickListener> captor = ArgumentCaptor.forClass(View.OnClickListener.class);
        verify(dateEditTextView).setOnClickListener(captor.capture());

        View.OnClickListener listener = captor.getValue();
        listener.onClick(null);

        verify(datePickerDialog).show();
    }

    @Test
    public void itChangesTheDateInTheEditTextViewWhenTheDialogIsDismissed() {
        subject.onPostCreate(activity);

        ArgumentCaptor<OnDateSetListener> captor = ArgumentCaptor.forClass(OnDateSetListener.class);

        verify(datePickerDialogFactory).make(eq(activity), captor.capture(), anyInt(), anyInt(), anyInt());

        OnDateSetListener listener = captor.getValue();
        listener.onDateSet(datePicker, 2017, 0, 1);

        String selectedDate = "2017/01/01";
        verify(dateEditTextView).setText(selectedDate);
    }
}