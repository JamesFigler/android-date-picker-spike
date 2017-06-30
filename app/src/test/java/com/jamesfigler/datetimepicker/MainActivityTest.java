package com.jamesfigler.datetimepicker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainActivityTest {

    @InjectMocks
    private MainActivity subject;

    @Mock
    private MainActivityImpl impl;

    @Before
    public void setUp() {
        initMocks(this);

        subject.closeValve();
    }

    @Test
    public void onCreateDelegatesToImpl() {
        subject.onCreate(null);

        verify(impl).onCreate(subject);
    }
}