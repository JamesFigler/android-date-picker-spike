package com.jamesfigler.datetimepicker;

import android.view.View;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityImplTest {

    @InjectMocks
    private MainActivityImpl subject;

    private MainActivity activity;
    private Button showDateTimePickerButton;

    @Before
    public void setUp() {
        initMocks(this);

        activity = mock(MainActivity.class);
        showDateTimePickerButton = mock(Button.class);

        when(activity.findViewById(R.id.show_date_picker_button)).thenReturn(showDateTimePickerButton);
    }

    @Test
    public void onCreateSetsTheContentView() {
        subject.onCreate(activity);

        verify(activity).setContentView(R.layout.activity_main);
    }

    @Test
    public void itSetsAClickListenerToShowTheDateTimePicker() {
        subject.onPostCreate(activity);

        ArgumentCaptor<View.OnClickListener> captor = ArgumentCaptor.forClass(View.OnClickListener.class);
        verify(showDateTimePickerButton).setOnClickListener(captor.capture());

        View.OnClickListener listener = captor.getValue();
        listener.onClick(null);

        verify(showDateTimePickerButton).setText("I've been clicked");
    }
}