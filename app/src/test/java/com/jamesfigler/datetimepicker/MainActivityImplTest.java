package com.jamesfigler.datetimepicker;

import android.app.FragmentManager;
import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.wdullaer.materialdatetimepicker.date.DatePickerDialog.Version.VERSION_2;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityImplTest {

    @Mock
    private DatePickerDialogFactory datePickerDialogFactory;

    @InjectMocks
    private MainActivityImpl subject;

    private MainActivity activity;
    private TextInputEditText dateEditTextView;
    private DatePickerDialog datePickerDialog;
    private FragmentManager fragmentManager;

    @Before
    public void setUp() {
        initMocks(this);

        activity = mock(MainActivity.class);
        dateEditTextView = mock(TextInputEditText.class);
        datePickerDialog = mock(DatePickerDialog.class);
        fragmentManager = mock(FragmentManager.class);

        when(activity.findViewById(R.id.date_input_view_text)).thenReturn(dateEditTextView);
        when(activity.getFragmentManager()).thenReturn(fragmentManager);
        when(datePickerDialogFactory.make()).thenReturn(datePickerDialog);
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

    @Test
    public void itInitializesTheDatePickerDialog() {
        subject.onPostCreate(activity);

        verify(datePickerDialog).setVersion(VERSION_2);
        verify(datePickerDialog).setMinDate(Calendar.getInstance());
        verify(datePickerDialog).vibrate(false);
    }

    @Test
    public void itShowsTheDatePickerDialogWhenClickingOnTheDate() {
        subject.onPostCreate(activity);

        ArgumentCaptor<View.OnClickListener> captor = ArgumentCaptor.forClass(View.OnClickListener.class);
        verify(dateEditTextView).setOnClickListener(captor.capture());

        View.OnClickListener listener = captor.getValue();
        listener.onClick(null);

        verify(datePickerDialog).show(fragmentManager, null);
    }

    @Test
    public void itChangesTheDateInTheEditTextViewWhenTheDialogIsDismissed() {
        subject.onPostCreate(activity);

        ArgumentCaptor<OnDateSetListener> captor = ArgumentCaptor.forClass(OnDateSetListener.class);
        verify(datePickerDialog).setOnDateSetListener(captor.capture());

        OnDateSetListener listener = captor.getValue();
        listener.onDateSet(datePickerDialog, 2017, 0, 1);

        String selectedDate = "2017/01/01";
        verify(dateEditTextView).setText(selectedDate);
    }
}