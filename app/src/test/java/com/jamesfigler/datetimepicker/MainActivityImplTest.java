package com.jamesfigler.datetimepicker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityImplTest {

    @InjectMocks
    private MainActivityImpl subject;

    private MainActivity activity;

    @Before
    public void setUp() {
        initMocks(this);

        activity = mock(MainActivity.class);
    }

    @Test
    public void onCreateSetsTheContentView() {
        subject.onCreate(activity);

        verify(activity).setContentView(R.layout.activity_main);
    }
}