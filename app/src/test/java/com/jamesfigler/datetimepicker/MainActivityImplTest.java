package com.jamesfigler.datetimepicker;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityImplTest {

    @InjectMocks
    private MainActivityImpl subject;

    private MainActivity activity;
    private TextInputEditText dateEditTextView;

    @Before
    public void setUp() {
        initMocks(this);

        activity = mock(MainActivity.class);
        dateEditTextView = mock(TextInputEditText.class);

        when(activity.findViewById(R.id.date_input_view_text)).thenReturn(dateEditTextView);
    }

    @Test
    public void onCreateSetsTheContentView() {
        subject.onCreate(activity);

        verify(activity).setContentView(R.layout.activity_main);
    }

    @Test
    public void itShowsTodaysDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        String formattedDate = sdf.format(calendar.getTime());

        subject.onPostCreate(activity);

        verify(dateEditTextView).setText(formattedDate);
    }
}